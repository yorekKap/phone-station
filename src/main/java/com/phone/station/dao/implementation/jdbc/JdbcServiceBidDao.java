
package com.phone.station.dao.implementation.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import com.phone.station.dao.builder.SelectQuery;
import com.phone.station.dao.interfaces.ServiceBidDao;
import com.phone.station.dao.parsers.ObjectToColumnValueMapParser;
import com.phone.station.dao.parsers.ResultSetParser;
import com.phone.station.entities.ServiceBid;
import com.phone.station.entities.enums.ServiceBidStatus;

public class JdbcServiceBidDao extends AbstractJdbcDao<ServiceBid, Long>
												implements ServiceBidDao{

	private static final String TABLE_NAME = "service_bids";

	//column names
	private static final String PK_COLUMN = "id";
	private static final String SERVICE_ID = "service_id";
	private static final String SUBSCRIBER_ID = "subscriber_id";
	private static final String STATUS = "status";
	private static final String ADMIN_COMMENT = "admin_comment";

	public JdbcServiceBidDao(DataSource dataSource) {
		super(dataSource);
	}

	@Override
	public ServiceBid prepareResultSet(ResultSet rs) throws SQLException {
		ResultSetParser parse = new ResultSetParser(rs);
		return parse.parseResultSetToObject(ServiceBid.class);

	}

	@Override
	public Map<String, Object> getValuesMap(ServiceBid object) {
		return ObjectToColumnValueMapParser.parse(object, ServiceBid.class);
	}

	@Override
	public void delete(ServiceBid object) {
		builder.delete()
		   .from(getTableName())
		   .where(getPK()).isEquals(object.getId())
		   .execute();
	}

	@Override
	public void provideInnerJoin(SelectQuery query) {
		// TODO Auto-generated method stub

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
	public List<ServiceBid> findByServiceId(Long serviceId) {
		return builder.select("*")
					  .where(SERVICE_ID).isEquals(serviceId)
					  .execute(this::prepareResultSet);
	}

	@Override
	public List<ServiceBid> findBySubscriberId(Long subscriberId) {
		return builder.select("*")
					  .where(SUBSCRIBER_ID).isEquals(subscriberId)
					  .execute(this::prepareResultSet);
	}

	@Override
	public List<ServiceBid> findByStatus(ServiceBidStatus status) {
		return builder.select("*")
						.where(STATUS).isEquals(status.ordinal())
						.execute(this::prepareResultSet);
	}

}
