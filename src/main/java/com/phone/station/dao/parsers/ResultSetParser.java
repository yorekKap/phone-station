package com.phone.station.dao.parsers;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.phone.station.dao.annotations.Column;
import com.phone.station.dao.annotations.ParseEnum;
import com.phone.station.dao.annotations.Table;


/**
 * Parses result set to object
 *
 * @author yuri
 *
 */
/**
 * @author yuri
 *
 */

public class ResultSetParser {
	private static final Logger log = Logger.getLogger(ResultSetParser.class);

	private ResultSet rs;

	/**
	 * Map that binds result set index(value)
	 * with TABLE_NAME.COLUMN_NAME key
	 * <p>
	 * It's done with to provide join support
	 * where the ambiguous column name is highly possible
	 */
	private Map<String, Integer> columns = new HashMap<>();


	public ResultSetParser(ResultSet rs){
		this.rs = rs;
		getColumnsFromResultSet();
	}


	/**
	 * Fetch {@link ResultSetMetaData} from {@link ResultSet}
	 * to initialize {@code #columns} map with
	 * corresponding column_names
	 */
	private void getColumnsFromResultSet(){
	 try {

		 ResultSetMetaData md = rs.getMetaData();
		 int count = md.getColumnCount();

		 String columnName = null;
		 for(int i = 1; i <= count; i++){
			 columnName = md.getTableName(i) + "." + md.getColumnName(i);
			 columns.put(columnName, i);
		 }

		 log.info("Columns map is initialized");

	 } catch (SQLException e) {
		log.error("SQLException in ResultSetParser : ", e);
		e.printStackTrace();
	}
	}


	/**
	 * Parse {@link ResultSet} to @{link Class} instance
	 *
	 * @param clazz which instance should be returned
	 * @return instance of {@code #clazz}
	 */
	public  <T> T parseResultSetToObject(Class<T> clazz){
		try {
			//get an instance of class that will
			//be returned as a result and it's fields
			T object = clazz.newInstance();
			Field[] fields = clazz.getDeclaredFields();

			//fetch table name from Table annotation
			Table table = clazz.getAnnotation(Table.class);
			String tableName = table.value();

			//iterate through all field annotated with Column
			//and set it's values to the corresponding value in ResultSet
			Arrays.stream(fields)
								  .filter(f -> f.getAnnotation(Column.class) != null)
								  .peek(f -> f.setAccessible(true))
								  .forEach(f -> parseResultSetColumn(f, object, rs, tableName));

			log.info("ResultSet is converted to " + clazz.getName());
			return object;

		} catch (InstantiationException | IllegalAccessException e) {
			log.error("Exception in ResultSetParser : ", e);
			throw new RuntimeException(e.getMessage());
		}

	}


	/**
	 * Parse {@link ResultSet} column to {@link Field}
	 *
	 * @param field {@link Field} that column value should assigned to
	 * @param obj {@link Object} that contains field
	 * @param rs {@link ResultSet} that should be parsed
	 * @param tableName name of the corresponding table
	 */
	private void parseResultSetColumn(Field field, Object obj, ResultSet rs, String tableName){
		//get full column name (table name + column name)
		String columnName = tableName + "." + field.getAnnotation(Column.class).value();

		try {
			Object value = null;
			if(field.getAnnotation(ParseEnum.class) != null){
				value = parseEnum(field, obj, rs, columnName);
			}
			else{
				Integer columnIndex = columns.get(columnName);
				if(columnIndex == null){
					log.error("No column with name" + columnName);
					return;
				}

				value = rs.getObject(columns.get(columnName));
			}

			if (field.getType().equals(Long.class) && value != null) {
					value = new Long((Integer)value);
			}

				field.set(obj, value);

		} catch (IllegalArgumentException | IllegalAccessException | SQLException e) {
			log.error("Exception in ResultSetParser : ", e);
			e.printStackTrace();
		} catch (SecurityException e) {
			log.error("SecurityException in ResultSetParser : ", e);
			e.printStackTrace();
		}
	}


	/**
	 * Parse column value to the enum
	 *
	 * @param field {@link Enum} field
	 * @param obj {@link Object} that contains field
	 * @param rs {@link ResultSet}
	 * @param columnName name of the column to parse
	 * @return parsed value
	 */
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

				log.error("Exception in ResultSetParser:", e);
				throw new RuntimeException();
			}


	}



}

