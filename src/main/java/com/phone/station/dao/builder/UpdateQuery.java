package com.phone.station.dao.builder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.phone.station.exceptions.dao.MySQLUpdateException;

/**
 * Used for the {@code UPDATE} query bui
 *
 * @author yuri
 *
 */
public class UpdateQuery extends WhereQuery<UpdateQuery>{
	private static final Logger log = Logger.getLogger(UpdateQuery.class);

	private Connection connection;
	private String tableName;
	private Map<String, Object> values = new HashMap<>();

	public UpdateQuery(Connection connection, String tableName) {
		super(UpdateQuery.class);
		this.connection = connection;
		this.tableName = tableName;
	}

	public UpdateQuery set(String columnName, Object value){
		values.put(columnName, value);
		return this;
	}

	public UpdateQuery setValuesMap(Map<String, Object> valuesMap){
		this.values = valuesMap;
		return this;
	}

	public int execute() {
		try(PreparedStatement statement = connection.prepareStatement(toString())){
			int i = 1;
			for(Object o : values.values()){
				statement.setObject(i++, o);
			}

			log.info(statement.toString() + " Update statement executing");
			return statement.executeUpdate();

		}catch(SQLException e){
			log.error("SQLException in UpdateQuery : ", e);
			throw new MySQLUpdateException(e.getMessage());
		}
	}

	public String toString(){
		StringBuilder sqlBuilder = new StringBuilder(" UPDATE ").append(tableName).append(" SET ");
		String[] columns = values.keySet()
										  .stream()
										  .map(s -> s + " = " + " ?")
										  .toArray(size -> new String[size]);

		sqlBuilder.append( String.join(", ", columns) );
		sqlBuilder.append(predicates.toString()).append(";");
		return sqlBuilder.toString();
	}
}