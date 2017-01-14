package com.phone.station.dao.builder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.phone.station.exceptions.dao.MySQLInsertException;

public class InsertQuery {

	private Connection connection;
	private String tableName;
	private Map<String, Object> values;

	public InsertQuery(Connection connection, String tableName){
		this.connection = connection;
		this.tableName = tableName;
		this.values = new HashMap<>();
	}

	public InsertQuery into(String tableName){
		this.tableName = tableName;
		return this;
	}

	public InsertQuery setValue(String column, Object value){
		values.put(column, value);
		return this;
	}

	public InsertQuery setValues(Map<String, Object> values){
		this.values = values;
		return this;
	}

	public int execute(){
		try (PreparedStatement statement = connection.prepareStatement(toString())){
			//set values
			int i = 1;
			for(Object o : values.values()){
					statement.setObject(i++, o);
			}

			System.out.println(statement.toString());
			return statement.executeUpdate();

		} catch (SQLException e) {
			throw new MySQLInsertException(e.getMessage());
		}
	}

	public String toString(){
		//question marks for the PreparedStatement
		String questionMarks = IntStream.range(0, values.size())
								  		   .mapToObj(i -> "?")
								           .collect(Collectors.joining(", ", "(", ") "));

		//columns that are to be inserted
		String columns = "(" + String.join(", ", values.keySet()) + ")";

		//INSERT INTO tableName(column1, column2) VALUES(?, ?, ...);
		StringBuilder sqlBuilder = new StringBuilder("INSERT INTO ")
													.append(tableName + columns)
													.append(" VALUES " + questionMarks + ";");
		System.out.println(sqlBuilder.toString());

		values.entrySet().stream().forEach(x -> System.out.println(x.getValue()));
		return sqlBuilder.toString();
	}


}
