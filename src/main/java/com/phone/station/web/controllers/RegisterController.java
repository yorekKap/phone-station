package com.phone.station.web.controllers;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Request;

import com.phone.station.entities.User;
import com.phone.station.entities.enums.Role;
import com.phone.station.service.interfaces.UserService;
import com.phone.station.web.dispatcher.Controller;

public class RegisterController extends Controller {

	UserService userService;

	//parameters names
	private static final String FIRST_NAME = "first_name";
	private static final String LAST_NAME = "last_name";
	private static final String PASSWORD = "password";
	private static final String USERNAME = "username";
	private static final String PHONE = "phone";

	private static final String LOGIN_URL = "/login";

	public RegisterController(UserService userService) {
		this.userService = userService;
	}

	@Override
	public String get(HttpServletRequest request, HttpServletResponse response) {
		return "register";
	}

	@Override
	public String post(HttpServletRequest request, HttpServletResponse response) {
		User user = getUser(request);

		if (userService.create(user)){
			try {
				response.sendRedirect(LOGIN_URL);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		else{
			request.setAttribute("user", user);
			request.setAttribute("failed", true);
			return "register";
		}
	}

	private User getUser(HttpServletRequest request){
		User user = new User();
		user.setFirstName(request.getParameter(FIRST_NAME));
		user.setLastName(request.getParameter(LAST_NAME));
		user.setPhone(request.getParameter(PHONE));
		user.setUsername(request.getParameter(USERNAME));
		user.setPassword(request.getParameter(PASSWORD));
		user.setUserRole(Role.USER);
		user.setRegistrationDate(new Date());

		return user;
	}
}
