package com.phone.station.dao.builder;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.phone.station.exceptions.dao.MySQLDeleteException;

public class DeleteQuery extends WhereQuery<DeleteQuery>{

	private Connection connection;
	private String tableName;
	
	public DeleteQuery(Connection connection, String tableName) {
		super(DeleteQuery.class);
		this.connection = connection;
		this.tableName = tableName;
	}
	
	public DeleteQuery from(String tableName){
		this.tableName = tableName;
		return this;
	}

	public int execute(){
		try(Statement statement = connection.createStatement()){
			System.out.println(toString());
			return statement.executeUpdate(toString());
		}catch(SQLException e){
			throw new MySQLDeleteException(e.getMessage());
		}
	}
	
	public String toString(){
		StringBuilder sqlBuilder = new StringBuilder("DELETE FROM ").append(tableName).append("\n")
				.append(predicates).append(";");
		return sqlBuilder.toString();

	}
}
