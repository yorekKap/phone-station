package com.phone.station.dao.implementation.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import javax.sql.DataSource;

import com.phone.station.dao.builder.SelectQuery;
import com.phone.station.dao.constants.OrderingMode;
import com.phone.station.dao.interfaces.NewsDao;
import com.phone.station.dao.parsers.ObjectToColumnValueMapParser;
import com.phone.station.dao.parsers.ResultSetParser;
import com.phone.station.entities.News;

public class JdbcNewsDao extends AbstractJdbcDao<News, Long> implements NewsDao{

	private static final String NEWS_TABLE = "news";
	private static final String NEWS_ID = NEWS_TABLE + "." + "id";
	private static final String NEWS_DATE = NEWS_TABLE + "." + "date";

	public JdbcNewsDao(DataSource dataSource) {
		super(dataSource);
	}

	@Override
	public void delete(News object) {
		builder.delete()
			   .where(NEWS_ID).isEquals(object.getId())
			   .execute();
	}

	@Override
	public News prepareResultSet(ResultSet rs) throws SQLException {
		ResultSetParser parser = new ResultSetParser(rs);
		return parser.parseResultSetToObject(News.class);
	}

	@Override
	public Map<String, Object> getValuesMap(News object) {
		return ObjectToColumnValueMapParser.parse(object, News.class);
	}

	@Override
	public String getTableName() {
		return NEWS_TABLE;
	}

	@Override
	public String getPK() {
		return NEWS_ID;
	}

	@Override
	public void provideInnerJoin(SelectQuery query) {
	}

	@Override
	public void provideOrdering(SelectQuery query) {
		query.orderBy(NEWS_DATE, OrderingMode.DESC);
	}



}
