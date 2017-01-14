package com.phone.station.web.dispatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.phone.station.config.WebAppContext;

public abstract class Controller {

	public static final String GET = "GET";
	public static final String POST = "POST";
	
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		 	String method = request.getMethod();
		 	String view;
		 	if(method.equals(GET)){
		 		 view =  get(request, response);
		 	}
		 	else{
		 		view = post(request, response);
		 	}
		 	
		 	return WebAppContext.get(ViewResolver.class).resolve(view);
	    }

	
	public String get(HttpServletRequest request, HttpServletResponse response) {
		return null;
	}

	public String post(HttpServletRequest request, HttpServletResponse response) {
		return null;
	}

}
