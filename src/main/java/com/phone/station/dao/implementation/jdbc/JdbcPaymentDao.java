package com.phone.station.dao.implementation.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import com.phone.station.dao.builder.SelectQuery;
import com.phone.station.dao.constants.OrderingMode;
import com.phone.station.dao.interfaces.PaymentDao;
import com.phone.station.dao.parsers.ObjectToColumnValueMapParser;
import com.phone.station.dao.parsers.ResultSetParser;
import com.phone.station.entities.Payment;


/**
 * DAO class for managing {@link Payment} entities
 *
 * @author yuri
 *
 */
public class JdbcPaymentDao extends AbstractJdbcDao<Payment, Long> implements PaymentDao{

	private static final String TABLE_NAME = "payments";

	private static final String PK_COLUMN = TABLE_NAME + ".id";
	private static final String USER_ID_COLUMN = TABLE_NAME + ".user_id";
	private static final String DATE_COLUMN =  TABLE_NAME + ".date";

	public JdbcPaymentDao(DataSource dataSource) {
		super(dataSource);
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public Payment prepareResultSet(ResultSet rs) throws SQLException {
		ResultSetParser parser = new ResultSetParser(rs);
		return parser.parseResultSetToObject(Payment.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map<String, Object> getValuesMap(Payment object) {
		return ObjectToColumnValueMapParser.parse(object, Payment.class);
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
	public void provideInnerJoin(SelectQuery query) {
	}

	@Override
	public void provideOrdering(SelectQuery query) {
		query.orderBy(DATE_COLUMN, OrderingMode.DESC);
	}

	@Override
	public void delete(Payment object) {
		builder.delete()
		   .from(getTableName())
		   .where(getPK()).isEquals(object.getId())
		   .execute();

	}

	@Override
	public List<Payment> findByUserId(Long userId) {
		return builder.select("*")
					  .where(USER_ID_COLUMN)
					  .isEquals(userId)
					  .execute(this::prepareResultSet);
	}

	@Override
	public List<Payment> findByUserIdOrderedByDate(Long userId) {
		return builder.select("*")
				  .where(USER_ID_COLUMN)
				  .isEquals(userId)
				  .orderBy(DATE_COLUMN, OrderingMode.DESC)
				  .execute(this::prepareResultSet);
	}

	@Override
	public List<Payment> findByUserIdOrderedByDate(Long userId, int offset, int limit) {
		return builder.select("*")
				  .where(USER_ID_COLUMN)
				  .isEquals(userId)
				  .orderBy(DATE_COLUMN, OrderingMode.DESC)
				  .limit(offset, limit)
				  .execute(this::prepareResultSet);

	}

	@Override
	public List<Payment> findAllOrderedByDate() {
		return builder.select("*")
					  .orderBy(DATE_COLUMN, OrderingMode.DESC)
					  .execute(this::prepareResultSet);
	}

	@Override
	public int getNumOfRecordsWithUserId(Long userId) {
		return builder.select("count(*)")
					  .where(USER_ID_COLUMN)
					  .isEquals(userId)
					  .executeForSingle(rs -> rs.getInt(1));
	}
}
