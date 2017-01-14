package com.phone.station.exceptions.dao;

public class MySQLBadQueryException extends MySQLException{
		
	public MySQLBadQueryException(String message) {
		super(message);
	}
}
