package com.phone.station.utils;

import javax.servlet.http.HttpServletRequest;

import com.phone.station.config.WebAppContext;
import com.phone.station.entities.User;
import com.phone.station.service.interfaces.UserService;
import com.phone.station.web.security.UserPrincipal;

public class CurrentUserFetcher {

	static UserService userService = WebAppContext.get(UserService.class);

	public static User getCurrentUser(HttpServletRequest request){
		String username = ((UserPrincipal)request.getSession().getAttribute("principal")).getName();
		return userService.findByUsername(username);
	}

	public static Long getCurrentUserId(HttpServletRequest request){
		return ((UserPrincipal)(request.getSession().getAttribute("principal"))).getId();
	}
}
