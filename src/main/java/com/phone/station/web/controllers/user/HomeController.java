package com.phone.station.web.controllers.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.phone.station.entities.User;
import com.phone.station.service.interfaces.UserService;
import com.phone.station.web.dispatcher.Controller;
import com.phone.station.web.security.UserPrincipal;

public class HomeController extends Controller {

	UserService userService;

	public HomeController(UserService userService) {
		this.userService = userService;
	}

	@Override
	public String get(HttpServletRequest request, HttpServletResponse response) {
		String username = ((UserPrincipal)request.getSession().getAttribute("principal")).getName();
		User user = userService.findByUsername(username);
		request.setAttribute("user", user);
		return "home";
	}

}
