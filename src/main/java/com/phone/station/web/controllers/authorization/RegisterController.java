package com.phone.station.web.controllers.authorization;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.phone.station.entities.User;
import com.phone.station.entities.enums.Role;
import com.phone.station.service.interfaces.UserService;
import com.phone.station.web.dispatcher.Controller;

public class RegisterController extends Controller {
	private static final Logger log = Logger.getLogger(RegisterController.class);

	UserService userService;

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
				log.info("User with username: "+ user.getUsername() + ";password: " +
						  user.getPassword() + "is registered");
				response.sendRedirect("/login");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		else{
			request.setAttribute("user", user);
			request.setAttribute("failed", true);
			log.info("Register is failed for user: " + user.toString());
			return "register";
		}
	}

	private User getUser(HttpServletRequest request){
		User user = new User();
		user.setFirstName(request.getParameter("first_name"));
		user.setLastName(request.getParameter("last_name"));
		user.setPhone(request.getParameter("phone"));
		user.setUsername(request.getParameter("username"));
		user.setPassword(request.getParameter("password"));
		user.setUserRole(Role.USER);
		user.setRegistrationDate(new Date());

		return user;
	}
}
