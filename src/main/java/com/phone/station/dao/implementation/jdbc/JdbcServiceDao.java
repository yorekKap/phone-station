package com.phone.station.dao.implementation.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import com.phone.station.dao.builder.SelectQuery;
import com.phone.station.dao.interfaces.ServiceDao;
import com.phone.station.dao.parsers.ObjectToColumnValueMapParser;
import com.phone.station.dao.parsers.ResultSetParser;
import com.phone.station.entities.Service;
import com.phone.station.exceptions.dao.MySQLException;

/**
 * DAO class for managing {@link Service} entities
 *
 * @author yuri
 *
 */
public class JdbcServiceDao extends AbstractJdbcDao<Service, Long> implements ServiceDao{

	private static final String TABLE_NAME = "services";

	//for join request
	private static final String JOIN_TABLE = "users_services";
	private static final String JOIN_SERVICE_ID = JOIN_TABLE + ".service_id";
	private static final String JOIN_USER_ID = JOIN_TABLE + ".user_id";
	private static final String USERS_TABLE = "users";
	private static final String USERS_PK = USERS_TABLE + ".id";

	//column names
	private static final String PK_COLUMN = TABLE_NAME + ".id";
	private static final String TITLE_COLUMN = TABLE_NAME + ".title";
	private static final String COST_COLUMN = TABLE_NAME + ".cost";

	public JdbcServiceDao(DataSource dataSource) {
		super(dataSource);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Service prepareResultSet(ResultSet rs) throws SQLException {
		ResultSetParser parser = new ResultSetParser(rs);
		return parser.parseResultSetToObject(Service.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map<String, Object> getValuesMap(Service service) {
		return ObjectToColumnValueMapParser.parse(service, Service.class);

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void provideInnerJoin(SelectQuery query) {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getTableName() {
		return TABLE_NAME;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getPK() {
		return PK_COLUMN;
	}

	@Override
	public void delete(Service object) {
		builder.delete()
			   .from(JOIN_TABLE)
			   .where(JOIN_SERVICE_ID).isEquals(object.getId())
			   .execute();

		builder.delete()
		   .from(getTableName())
		   .where(getPK()).isEquals(object.getId())
		   .execute();
	}

	@Override
	public Service findByTitle(String title) {
		return builder.select("*")
					  .where(TITLE_COLUMN).isEquals(title)
					  .executeForSingle(this::prepareResultSet);
	}

	@Override
	public List<Service> findWithCostBetween(double start, double end) {
		return builder.select("*")
					  .where(COST_COLUMN).beetween(start, end)
					  .execute(this::prepareResultSet);
	}

	@Override
	public List<Service> findAllOfUser(Long userId) {
		return builder.select("*")
				   .join(JOIN_TABLE).on(PK_COLUMN, JOIN_SERVICE_ID)
				   .join(USERS_TABLE).on(JOIN_USER_ID, USERS_PK)
				   .where(USERS_PK).isEquals(userId)
				   .execute(this::prepareResultSet);
	}

	@Override
	public List<Service> findAllNotOfUser(Long userId) {
		SelectQuery innerQuery = builder.select(JOIN_SERVICE_ID)
									    .from(JOIN_TABLE)
									    .where(JOIN_USER_ID).isEquals(userId);

		return builder.select("*")
					  .from(TABLE_NAME)
					  .where(PK_COLUMN).notIn(innerQuery)
					  .execute(this::prepareResultSet);
	}

	@Override
	public boolean addServiceToUser(Long serviceId, Long userId) {
		try{
			builder.insert().into(JOIN_TABLE)
				   .setValue(JOIN_SERVICE_ID, serviceId)
				   .setValue(JOIN_USER_ID, userId)
				   .execute();

		}catch(MySQLException e){
			e.printStackTrace();
			return false;
		}

		return true;
	}

	@Override
	public boolean removeSeviceFromUser(Long serviceId, Long userId) {
		try{
			builder.delete()
			   .from(JOIN_TABLE)
			   .where(JOIN_SERVICE_ID).isEquals(serviceId)
			   .and(JOIN_USER_ID).isEquals(userId)
			   .execute();

		}catch(MySQLException e){
			e.printStackTrace();
			return false;
		}

		return true;
	}

	}
