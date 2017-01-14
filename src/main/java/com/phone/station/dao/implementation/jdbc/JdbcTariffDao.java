package com.phone.station.dao.implementation.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import javax.sql.DataSource;

import com.phone.station.dao.builder.SelectQuery;
import com.phone.station.dao.interfaces.TariffDao;
import com.phone.station.dao.parsers.ObjectToColumnValueMapParser;
import com.phone.station.dao.parsers.ResultSetParser;
import com.phone.station.entities.Tariff;

public class JdbcTariffDao extends AbstractJdbcDao<Tariff, Long> implements TariffDao{

	private static final String	TABLE_NAME = "tariffs";

	//column names
	private static final String PK_COLUMN = "id";
	private static final String TITLE = "title";

	private static final String USERS_TABLE = "users";
	private static final String USERS_ID = USERS_TABLE + ".id";
	private static final String USERS_TARIF_ID = USERS_TABLE + ".tariff_id";

	public JdbcTariffDao(DataSource dataSource) {
		super(dataSource);
		// TODO Auto-generated constructor stub
	}
	@Override
	public Tariff prepareResultSet(ResultSet rs) throws SQLException {
		ResultSetParser parser = new ResultSetParser(rs);
		Tariff tariff = parser.parseResultSetToObject(Tariff.class);

		return tariff;
	}

	@Override
	public Map<String, Object> getValuesMap(Tariff object) {
		return ObjectToColumnValueMapParser.parse(object, Tariff.class);
	}

	@Override
	public void provideInnerJoin(SelectQuery query) {
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
	public void delete(Tariff object) {
		builder.update(USERS_TABLE)
			   .set(USERS_TARIF_ID, null)
			   .where(USERS_TARIF_ID).isEquals(object.getId())
			   .execute();

		builder.delete()
		   .from(getTableName())
		   .where(getPK()).isEquals(object.getId())
		   .execute();

	}

	@Override
	public Tariff findByTitle(String title) {
		return builder.select("*")
					  .where(TITLE).isEquals(title)
					  .executeForSingle(this::prepareResultSet);

	}

}
