package com.phone.station.config;

import java.util.HashMap;
import java.util.Map;

public class Components {

	private Map<Class<?>, Object> components = new HashMap<>();
	
	public <T> Components add(Class<T> clazz, Object object){
		components.put(clazz, object);
		return this;
	}
	
	public <T> T get(Class<T> clazz){
		return clazz.cast(components.get(clazz));
	}
}
