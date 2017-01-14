package com.phone.station.config;

import java.util.Arrays;

public class WebAppContext {

	private static Components components;

	static{
		components = new Components();
		init();
	}

	private static void init(){
		Arrays.stream( getConfigs() ).forEach(c -> c.init(components));
	}

	private static Config[] getConfigs(){
		return new Config[] {
					new DaoConfig(),
					new ServiceConfig(),
					new RootConfig(),
					new WebConfig(),
					new SecurityConfig()
			};
	}
	public static <T> T get(Class<T> clazz){
		T comp = components.get(clazz);
		if(comp == null){
			throw new IllegalArgumentException("No binding for the class:" + clazz.getName());
		}

		return components.get(clazz);
	}

}
