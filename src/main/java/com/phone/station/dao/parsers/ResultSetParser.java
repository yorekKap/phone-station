package com.phone.station.dao.parsers;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.mockito.Matchers.anyObject;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.phone.station.dao.annotations.Column;
import com.phone.station.dao.annotations.ParseEnum;
import com.phone.station.dao.annotations.Table;


public class ResultSetParser {

	private ResultSet rs;

	Map<String, Integer> columns = new HashMap<>();

	public ResultSetParser(ResultSet rs){
		this.rs = rs;
		getColumnsFromResultSet();
	}

	private void getColumnsFromResultSet(){
	 try {

		 ResultSetMetaData md = rs.getMetaData();
		 int count = md.getColumnCount();

		 String columnName = null;
		 for(int i = 1; i <= count; i++){
			 columnName = md.getTableName(i) + "." + md.getColumnName(i);
			 columns.put(columnName, i);
		 }

	 } catch (SQLException e) {
		e.printStackTrace();
	}
	}

	public  <T> T parseResultSetToObject(Class<T> clazz){
		try {
			T object = clazz.newInstance();
			Field[] fields = clazz.getDeclaredFields();

			Table table = clazz.getAnnotation(Table.class);
			String tableName = table.value();

			Arrays.stream(fields)
								  .filter(f -> f.getAnnotation(Column.class) != null)
								  .peek(f -> f.setAccessible(true))
								  .forEach(f -> parseResultSetColumn(f, object, rs, tableName));
			return object;

		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}

	}

	private void parseResultSetColumn(Field field, Object obj, ResultSet rs, String tableName){
		String columnName = tableName + "." + field.getAnnotation(Column.class).value();
		try {
			Object value = null;
			if(field.getAnnotation(ParseEnum.class) != null){
				value = parseEnum(field, obj, rs, columnName);
			}
			else{
				Integer columnIndex = columns.get(columnName);
				if(columnIndex == null){
					return;
				}

				value = rs.getObject(columns.get(columnName));
			}

			if (field.getType().equals(Long.class) && value != null) {
					value = new Long((Integer) value);
			}
				field.set(obj, value);

		} catch (IllegalArgumentException | IllegalAccessException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Object parseEnum(Field field, Object obj, ResultSet rs, String columnName){
			Class<?> enumClass =  field.getType();
			Method valueOf;
			try {
				valueOf = enumClass.getDeclaredMethod("valueOf", String.class);
				String columnValue = (String)rs.getObject(columns.get(columnName));
				return valueOf.invoke(null, columnValue);
			} catch (NoSuchMethodException | SecurityException |
					 SQLException | IllegalAccessException |
					 IllegalArgumentException | InvocationTargetException e) {

				e.printStackTrace();
				throw new RuntimeException();
			}


	}



}

