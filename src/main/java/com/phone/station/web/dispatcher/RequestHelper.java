package com.phone.station.web.dispatcher;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import com.phone.station.config.WebAppContext;
import com.phone.station.exceptions.dispatcher.BadRequestException;
import com.phone.station.utils.ContextPathResolver;

public class RequestHelper {

	ControllersMapper mapper;

	public RequestHelper(ControllersMapper mapper){
		this.mapper = mapper;
	}

	public Controller getController(HttpServletRequest request){
		String url = ContextPathResolver.getContextPath(request);
		Controller controller = WebAppContext.get(ControllersMapper.class).get(url);
			if(controller == null){
				throw new BadRequestException("There is no controller for " + url);
			}

		return controller;
	}
}
