package com.phone.station.web.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.phone.station.entities.User;
import com.phone.station.entities.enums.Role;
import com.phone.station.service.interfaces.UserService;
import com.phone.station.web.dispatcher.Controller;

public class AdminUsersController extends Controller{

	private static final String USERS_ATTRIBUTE = "users";
	private static final String ACTION_PARAMETER = "action";
	private static final String DISCONNECT_USER = "disconnect-user";
	private static final String FAIL = "fail";

	private static final String USERS_URL = "admin-users";


	UserService userService;

	public AdminUsersController(UserService userService) {
		this.userService = userService;
	}

	@Override
	public String get(HttpServletRequest request, HttpServletResponse response) {
		List<User> users = userService.findAllWithRole(Role.USER);
		request.setAttribute(USERS_ATTRIBUTE, users);
		return USERS_URL;

	}

	@Override
	public String post(HttpServletRequest request, HttpServletResponse response) {
		String action = request.getParameter(ACTION_PARAMETER);

		if(action != null){
			if(action.equals(DISCONNECT_USER)){
				return disconnectUser(request, response);
			}
			else{
				return null;
			}
		}
		else{
			try {
				response.getOutputStream().write(FAIL.getBytes());
				response.flushBuffer();
				return null;
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}
	}

	private String disconnectUser(HttpServletRequest request, HttpServletResponse response){
		try{
			Long userId = Long.valueOf(request.getParameter("userId"));
			User user = userService.findById(userId);
			userService.delete(user);
			return null;
		}catch (NumberFormatException e) {
			try {
				response.getOutputStream().write(FAIL.getBytes());
				response.flushBuffer();
				e.printStackTrace();
				return null;
			} catch (IOException e1) {
				e1.printStackTrace();
				return null;
			}
		}
		}

}
