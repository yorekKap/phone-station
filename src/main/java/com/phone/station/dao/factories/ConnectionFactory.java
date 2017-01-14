package com.phone.station.dao.factories;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

public class ConnectionFactory {

	DataSource dataSource;
	
	public ConnectionFactory(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public Connection getConnection()throws SQLException{
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
}
