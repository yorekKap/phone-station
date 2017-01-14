package com.phone.station.dao.builder;

import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface
public interface PrepareResultSetFunction<T> {
	
	T prepareResultSet(ResultSet rs) throws SQLException;

}
