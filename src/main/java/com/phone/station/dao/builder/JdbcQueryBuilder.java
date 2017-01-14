package com.phone.station.dao.builder;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;

import com.phone.station.exceptions.dao.MySQLException;
import com.phone.station.exceptions.dao.MySQLTransactionException;

public class JdbcQueryBuilder {

	private	DataSource dataSource;
	private String tableName;
	private Connection tempConnection;
	
	public JdbcQueryBuilder(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public JdbcQueryBuilder(DataSource dataSource, String tableName) {
		this.dataSource = dataSource;
		this.tableName = tableName;
	}

	public void setTableName(String tableName){
		this.tableName = tableName;
	}
	
	public SelectQuery select(String... columnNames){
		return new SelectQuery(getConnection(), columnNames, tableName);
	}
	
	public InsertQuery insert(){
		return new InsertQuery(getConnection(), tableName);
	}
	
	public UpdateQuery update(String tableName){
		return new UpdateQuery(getConnection(), tableName);
	}
	
	public DeleteQuery delete(){
		return new DeleteQuery(getConnection(), tableName);
	}
	
	public void beginTransaction(){
		if(tempConnection != null){
			throw new MySQLTransactionException("There is already another transaction started");
		}
		
		try{
			tempConnection = dataSource.getConnection();
			tempConnection.setAutoCommit(false);
		}catch(SQLException e){
			throw new MySQLException(e.getMessage());
		}
	}
	
	public void commit(){
		if(tempConnection == null)
			throw new MySQLTransactionException("No transaction");
		try {
			tempConnection.commit();
			tempConnection = null;
		} catch (SQLException e) {
			throw new MySQLTransactionException(e.getMessage());
		}
	}
	
	public void rollback(){
		if(tempConnection == null){
			throw new MySQLTransactionException("No transaction");
		}
		
		try {
			tempConnection.rollback();
			tempConnection = null;
		} catch (SQLException e) {
			throw new MySQLTransactionException(e.getMessage());
		}
	}
	
	private Connection getConnection(){
		if(tempConnection != null)
			return tempConnection;
		
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			throw new MySQLException(e.getMessage());
		}
	}
}