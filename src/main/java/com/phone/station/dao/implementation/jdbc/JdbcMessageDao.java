package com.phone.station.dao.implementation.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import com.phone.station.dao.builder.SelectQuery;
import com.phone.station.dao.interfaces.MessageDao;
import com.phone.station.dao.parsers.ObjectToColumnValueMapParser;
import com.phone.station.dao.parsers.ResultSetParser;
import com.phone.station.entities.Message;

public class JdbcMessageDao extends AbstractJdbcDao<Message, Long> implements MessageDao {

	private static final String TABLE_NAME = "messages";
	private static final String PK_COLUMN = TABLE_NAME + ".id";
	private static final String USER_ID_COLUMN = TABLE_NAME + ".user_id";
	private static final String SENDER_COLUMN = TABLE_NAME + ".sender";

	public JdbcMessageDao(DataSource dataSource) {
		super(dataSource);
	}

	@Override
	public Message prepareResultSet(ResultSet rs) throws SQLException {
		ResultSetParser parser = new ResultSetParser(rs);
		return parser.parseResultSetToObject(Message.class);
	}

	@Override
	public Map<String, Object> getValuesMap(Message object) {
		return ObjectToColumnValueMapParser.parse(object, Message.class);
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
	public void provideInnerJoin(SelectQuery query) {
	}

	@Override
	public void delete(Message object) {
		builder.delete()
			   .from(getTableName())
			   .where(getPK()).isEquals(object.getId())
			   .execute();
	}


	@Override
	public List<Message> findByUserId(Long userId) {
		return builder.select("*")
					  .where(USER_ID_COLUMN).isEquals(userId)
					  .execute(this::prepareResultSet);
	}

	@Override
	public List<Message> findBySender(String sender) {
		return builder.select("*")
					  .where(SENDER_COLUMN).isEquals(sender)
					  .execute(this::prepareResultSet);
	}

}
