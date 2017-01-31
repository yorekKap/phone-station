package com.phone.station.dao.implementation.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import com.phone.station.dao.builder.JdbcQueryBuilder;
import com.phone.station.dao.builder.SelectQuery;
import com.phone.station.dao.interfaces.GenericDao;
import com.phone.station.entities.Identified;
import com.phone.station.exceptions.dao.MySQLException;


/**
 * Generic JDBC implementation of {@link GenericDao}
 *
 * @author yuri
 *
 * @param <T> entity that DAO operates on, should implement {@link Identified}
 * @param <K> primary key type
 */
/**
 * @author yuri
 *
 * @param <T>
 * @param <K>
 */
public abstract class AbstractJdbcDao<T extends Identified<Long>, K extends Number> implements GenericDao<T, K>{

	/**
	 * Converts result set into entity type
	 *
	 * @param rs {@link ResultSet} to convert
	 * @return entity
	 * @throws SQLException
	 */
	public abstract T prepareResultSet(ResultSet rs) throws SQLException;

	/**
	 * Convert entity to values map for it's later
	 * insertion in the UPDATE/INSERT queries
	 *
	 * @param object entity to be converted
	 * @return {@link Map} of column name - value pairs
	 */
	public abstract Map<String, Object> getValuesMap(T object);


	/**
	 * @return name of corresponding table
	 */
	public abstract String getTableName();


	/**
	 * @return primary key column of corresponding table
	 */
	public abstract String getPK();


	/**
	 * Provide default select query with {@code Join}(if necessary)
	 *
	 * @param query that should be provided with {@code Join}
	 */
	public abstract void provideInnerJoin(SelectQuery query);


	/**
	 * Provide default select query with ordering(if necessary)
	 *
	 * @param query that should be provided with ordering
	 */

	public abstract void provideOrdering(SelectQuery query);

	/**
	 * Main builder for all queries
	 */
	protected JdbcQueryBuilder builder;

	public AbstractJdbcDao(DataSource dataSource) {
		this.builder = new JdbcQueryBuilder(dataSource);
		this.builder.setTableName(getTableName());
	}

	@Override
	public boolean persist(T object) {
		try {
			builder.beginTransaction();

			builder.insert().into(getTableName()).setValues(getValuesMap(object)).execute();

			String maxId = "max(" + getPK() + ")";
			Long id =  builder.select(maxId)
							 .from(getTableName())
							 .executeForSingle(rs -> rs.getLong(1));
			builder.commit();

			object.setId(id);
		} catch (MySQLException e) {
			builder.rollback();
			return false;
		}

		return true;
	}

	@Override
	public T findByPK(K pk) {
		SelectQuery query = builder.select("*").from(getTableName())
								.where(getPK()).isEquals(pk);
		provideInnerJoin(query);
		return query.executeForSingle(this::prepareResultSet);

	}

	@Override
	public List<T> findAll() {
		SelectQuery query = builder.select("*")
						   .from(getTableName());
		provideInnerJoin(query);
		provideOrdering(query);

		return query.execute(this::prepareResultSet);
	}

	@Override
	public List<T> findAll(int offset, int limit) {
		SelectQuery query = builder.select("*")
							 	   .from(getTableName());
		provideInnerJoin(query);
		provideOrdering(query);
		return query.limit(offset, limit).execute(this::prepareResultSet);

	}

	@Override
	public void update(T object) {
		builder.update(getTableName())
					.setValuesMap(getValuesMap(object))
					.where(getPK()).isEquals(object.getId())
					.execute();
	}

	@Override
	public int getNumOfRecords() {
		return builder.select("count(*)")
					  .from(getTableName())
					  .executeForSingle(rs -> rs.getInt(1));
	}

}
