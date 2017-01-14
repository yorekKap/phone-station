package com.phone.station.dao.annotations.enums;

import static org.mockito.Mockito.RETURNS_DEEP_STUBS;

import java.util.Date;

import org.apache.naming.java.javaURLContextFactory;

public enum DateType {
	DATE{
		@Override
		public Object getDateInRightFormat(Date date) {
			return new java.sql.Date(date.getTime());
		}
	},

	TIME{
		@Override
		public Object getDateInRightFormat(Date date) {
			return new java.sql.Time(date.getTime());
		}

	},

	DATE_TIME{
		@Override
		public Object getDateInRightFormat(Date date) {
			return new java.sql.Timestamp(date.getTime());
		}
	};

	public abstract Object getDateInRightFormat(Date date);
}
