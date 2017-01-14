package com.phone.station.dao.factories;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

public class DataSourceFactory {

	public static final String MY_SQL = "mysql";

	public static DataSource getDataSource(String dbType){
		InitialContext initContext = null;

		try {
			initContext = new InitialContext();
		} catch (NamingException e) {
			e.printStackTrace();
		}

		if(dbType.equals(MY_SQL)){

			DataSource ds = null;
			try {
				ds = (DataSource) initContext.lookup("java:comp/env/jdbc/phone_station");
			} catch (NamingException e) {
				e.printStackTrace();
			}

			return ds;
		}

		else{
			throw new IllegalArgumentException("No such database template in factory");
		}
	}



}


