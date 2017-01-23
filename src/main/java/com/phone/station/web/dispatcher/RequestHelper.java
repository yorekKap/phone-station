package com.phone.station.web.dispatcher;

import javax.servlet.http.HttpServletRequest;

import com.phone.station.config.WebAppContext;
import com.phone.station.exceptions.dispatcher.BadRequestException;
import com.phone.station.utils.ContextPathFetcher;

/**
 * Serve for fetching right {@link Controller} by means of
 * {@link ControllersMapper}
 *
 * @author yuri
 */
public class RequestHelper {

	ControllersMapper mapper;

	public RequestHelper(ControllersMapper mapper){
		this.mapper = mapper;
	}

	public Controller getController(HttpServletRequest request){
		String url = ContextPathFetcher.getContextPath(request);
		Controller controller = WebAppContext.get(ControllersMapper.class).get(url);
			if(controller == null){
				throw new BadRequestException("There is no controller for " + url);
			}

		return controller;
	}
}
