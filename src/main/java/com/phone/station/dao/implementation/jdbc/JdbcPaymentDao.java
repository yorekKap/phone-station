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

public class JdbcPaymentDao extends AbstractJdbcDao<Payment, Long> implements PaymentDao{


	private static final String TABLE_NAME = "payments";

	private static final String PK_COLUMN = "id";
	private static final String USER_ID_COLUMN = "user_id";
	private static final String DATE_COLUMN = "date";

	public JdbcPaymentDao(DataSource dataSource) {
		super(dataSource);
	}

	@Override
	public Payment prepareResultSet(ResultSet rs) throws SQLException {
		ResultSetParser parser = new ResultSetParser(rs);
		return parser.parseResultSetToObject(Payment.class);
	}

	@Override
	public Map<String, Object> getValuesMap(Payment object) {
		return ObjectToColumnValueMapParser.parse(object, Payment.class);
	}

	@Override
	public String getTableName() {
		return TABLE_NAME;
	}

	@Override
	public String getPK() {
		return PK_COLUMN;
	}

	@Override
	public void delete(Payment object) {
		builder.delete()
		   .from(getTableName())
		   .where(getPK()).isEquals(object.getId())
		   .execute();

	}

	@Override
	public void provideInnerJoin(SelectQuery query) {
	}

	@Override
	public List<Payment> findByUserId(Long userId) {
		return builder.select("*")
					  .where("user_id")
					  .isEquals(userId)
					  .execute(this::prepareResultSet);
	}

	@Override
	public List<Payment> findByUserIdOrderedByDate(Long userId) {
		return builder.select("*")
				  .where("user_id")
				  .isEquals(userId)
				  .orderBy(DATE_COLUMN, OrderingMode.DESC)
				  .execute(this::prepareResultSet);
	}

	@Override
	public List<Payment> findAllOrderedByDate() {
		return builder.select("*")
					  .orderBy(DATE_COLUMN, OrderingMode.DESC)
					  .execute(this::prepareResultSet);
	}

}
