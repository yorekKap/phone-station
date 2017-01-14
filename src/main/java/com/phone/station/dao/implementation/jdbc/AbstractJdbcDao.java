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

public abstract class AbstractJdbcDao<T extends Identified<Long>, K extends Number> implements GenericDao<T, K>{

	public abstract T prepareResultSet(ResultSet rs) throws SQLException;
	public abstract Map<String, Object> getValuesMap(T object);

	public abstract String getTableName();
	public abstract String getPK();
	public abstract void provideInnerJoin(SelectQuery query);


	protected JdbcQueryBuilder builder;

	public AbstractJdbcDao(DataSource dataSource) {
		this.builder = new JdbcQueryBuilder(dataSource);
		this.builder.setTableName(getTableName());
	}

	@Override
	public boolean persist(T object) {
		try {
			builder.insert().into(getTableName()).setValues(getValuesMap(object)).execute();
		} catch (MySQLException e) {
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
		return query.execute(this::prepareResultSet);
	}

	@Override
	public void update(T object) {
		builder.update(getTableName())
					.setValuesMap(getValuesMap(object))
					.where(getPK()).isEquals(object.getId())
					.execute();
	}



}
