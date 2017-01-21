package com.phone.station.dao.builder;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

import com.phone.station.exceptions.dao.MySQLException;
import com.phone.station.exceptions.dao.MySQLTransactionException;

/**
 * Used to get right query builder and manage transactions
 *
 * @author yuri
 *
 */
public class JdbcQueryBuilder {
	private Logger log = Logger.getLogger(JdbcQueryBuilder.class);

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
			log.error("There is already another transaction started");
			throw new MySQLTransactionException("There is already another transaction started");
		}

		try{
			log.info("Getting connection");
			tempConnection = dataSource.getConnection();
			tempConnection.setAutoCommit(false);
		}catch(SQLException e){
			log.error("SQLException in JdbcQueryBuilder :", e);
			throw new MySQLException(e.getMessage());
		}
	}

	public void commit(){
		if(tempConnection == null){
			log.error("No transaction");
			throw new MySQLTransactionException("No transaction");
		}
		try {
			tempConnection.commit();
			tempConnection = null;
			log.info("Committed connection");
		} catch (SQLException e) {
			log.error("SQlException in JdbcQueryBuilder : ", e);
			throw new MySQLTransactionException(e.getMessage());
		}
	}

	public void rollback(){
		if(tempConnection == null)
		{
			log.error("No transaction to rollback");
			throw new MySQLTransactionException("No transaction");
		}

		try {
			tempConnection.rollback();
			tempConnection = null;
			log.info("Rollback transaction");
		} catch (SQLException e) {
			log.error("SQLException in JdbcQueryBuilder", e);
			throw new MySQLTransactionException(e.getMessage());
		}
	}

	private Connection getConnection(){
		if(tempConnection != null){
			log.info("Return already existed connection");
			return tempConnection;
		}

		try {
			log.info("Return new connection from DataSource");
			return dataSource.getConnection();
		} catch (SQLException e) {
			log.error("SQLException in JdbcQueryBuilder", e);
			throw new MySQLException(e.getMessage());
		}
	}
}