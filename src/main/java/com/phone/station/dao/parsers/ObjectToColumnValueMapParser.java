package com.phone.station.dao.parsers;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.phone.station.dao.annotations.Column;
import com.phone.station.dao.annotations.DateSQL;
import com.phone.station.dao.annotations.Id;

public class ObjectToColumnValueMapParser {


	public static <T> Map<String, Object> parse(T object, Class<?> clazz){
		Map<String, Object> result = new HashMap<>();
		Field[] fields = clazz.getDeclaredFields();

		Arrays.stream(fields)
							 .filter(f -> f.getAnnotation(Column.class) != null &&
							 			  f.getAnnotation(Id.class) == null)
							 .peek(f -> f.setAccessible(true))
							 .forEach(f -> parseObjectField(f, object, result));

		return result;
	}

	private static void parseObjectField(Field field, Object obj, Map<String, Object> result){
		String columnName = field.getAnnotation(Column.class).value();
		try {
			DateSQL date;
			Object value = null;
			if(Enum.class.isAssignableFrom(field.getType())){
				value = parseEnum(field, obj);
			}
			else if((date = field.getAnnotation(DateSQL.class)) != null){
				java.util.Date fieldDate = (java.util.Date)field.get(obj);
				value = date.value().getDateInRightFormat((fieldDate));
			}
			else{
				value = field.get(obj);
			}

			result.put(columnName, value);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	private static Object parseEnum(Field field, Object obj){
		try {
			Enum enumObject =  (Enum)field.get(obj);
			return enumObject.name();
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}

	}
}
