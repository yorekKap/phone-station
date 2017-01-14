package com.phone.station.web.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.phone.station.service.interfaces.UserService;
import com.phone.station.web.dispatcher.Controller;

public class AdminController extends Controller {

	private final String ADMIN_USERNAME = "Admin";

	UserService userService;

	public AdminController(UserService userService) {
		this.userService = userService;
	}

	@Override
	public String get(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("admin", userService.findByUsername(ADMIN_USERNAME));
		return "admin";
	}


}
