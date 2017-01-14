package com.phone.station.web.dispatcher;

import java.util.HashMap;
import java.util.Map;

public class ControllersMapper {

	Map<String, Controller> controllers = new HashMap<>();
	
	public ControllersMapper add(String url, Controller controller){
		controllers.put(url, controller);
		return this;
	}
	
	public Controller get(String url){
		return controllers.get(url);
	}


}
