package com.phone.station.dao.implementation.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import com.phone.station.dao.builder.SelectQuery;
import com.phone.station.dao.interfaces.TariffDao;
import com.phone.station.dao.interfaces.UserDao;
import com.phone.station.dao.parsers.ObjectToColumnValueMapParser;
import com.phone.station.dao.parsers.ResultSetParser;
import com.phone.station.entities.Tariff;
import com.phone.station.entities.User;
import com.phone.station.entities.enums.Role;

public class JdbcUserDao extends AbstractJdbcDao<User, Long> implements UserDao{

	private static final String TABLE_NAME = "users";
	private static final String TARRIF_TABLE = "tariffs";

	//user column names
	private static final String PK_COLUMN = TABLE_NAME + ".id";
	private static final String USERNAME = TABLE_NAME + ".username";
	private static final String PHONE = TABLE_NAME + ".phone";
	private static final String ADDITIONAL_PHONE = TABLE_NAME + ".additional_phone";
	private static final String TARIFF_ID = TABLE_NAME + ".tariff_id";
	private static final String USER_ROLE = TABLE_NAME + ".user_role";

	//tariff column names
	private static final String TARIFF_PK = TARRIF_TABLE + ".id";

	//users_services table
	private static final String USERS_SERVICES_TABLE = "users_services";
	private static final String USER_ID = USERS_SERVICES_TABLE + ".user_id";
	private static final String SERVICE_ID = USERS_SERVICES_TABLE + ".service_id";

	public JdbcUserDao(DataSource dataSource) {
		super(dataSource);
	}

	@Override
	public User prepareResultSet(ResultSet rs) throws SQLException {
		ResultSetParser parser = new ResultSetParser(rs);

		User user = parser.parseResultSetToObject(User.class);
		if (user.getTariffId() != null) {
			Tariff tariff = parser.parseResultSetToObject(Tariff.class);
			user.setTariff(tariff);
		}

		return user;
	}

	@Override
	public Map<String, Object> getValuesMap(User object) {
		return ObjectToColumnValueMapParser.parse(object, User.class);
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
		query.leftJoin(TARRIF_TABLE).on(TARIFF_ID, TARIFF_PK);
	}

	@Override
	public void delete(User object) {

		builder.delete()
			   .from(USERS_SERVICES_TABLE)
			   .where(USER_ID).isEquals(object.getId())
			   .execute();

		builder.delete()
			   .from(getTableName())
		       .where(getPK()).isEquals(object.getId())
		       .execute();

	}

	@Override
	public User findByPhone(String phone) {
		return builder.select("*")
			   .leftJoin(TARRIF_TABLE)
			   .on(TARIFF_ID, TARIFF_PK)
			   .where(PHONE).isEquals(phone)
			   .or(ADDITIONAL_PHONE).isEquals(phone)
			   .executeForSingle(this::prepareResultSet);
	}

	@Override
	public User findByUsername(String username) {
		return builder.select("*")
				.leftJoin(TARRIF_TABLE)
				.on(TARIFF_ID, TARIFF_PK)
				.where(USERNAME).isEquals(username)
				.executeForSingle(this::prepareResultSet);
	}

	@Override
	public List<User> findAllWithRole(Role role) {
		return builder.select("*")
				.leftJoin(TARRIF_TABLE)
				.on(TARIFF_ID, TARIFF_PK)
				.where(USER_ROLE).isEquals(role.name())
				.execute(this::prepareResultSet);
	}

}
