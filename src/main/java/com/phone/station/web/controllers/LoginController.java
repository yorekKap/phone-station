package com.phone.station.web.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.phone.station.entities.enums.Role;
import com.phone.station.exceptions.service.AuthenticationException;
import com.phone.station.service.interfaces.AuthenticationService;
import com.phone.station.web.dispatcher.Controller;
import com.phone.station.web.security.UserPrincipal;

public class LoginController extends Controller{

	private final static String HOME_URL = "/home";
	private final static String ADMIN_URL = "/admin";


	AuthenticationService authService;

	public LoginController(AuthenticationService authService) {
		this.authService = authService;
	}

	@Override
	public String get(HttpServletRequest request, HttpServletResponse response) {
		return "login";
	}

	@Override
	public String post(HttpServletRequest request, HttpServletResponse response) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		UserPrincipal principal = null;

		try{
			principal = authService.login(username, password);
			request.getSession().setAttribute("principal", principal);
			request.getSession().setMaxInactiveInterval(20 * 60);

			if(principal.getRole() == Role.ADMIN){
				response.sendRedirect(ADMIN_URL);
			}
			else{
				response.sendRedirect(HOME_URL);
			}
			return null;

		}catch(AuthenticationException e){
			request.setAttribute("fail", e.getReason());
			request.setAttribute("fusername", username);
			request.setAttribute("fpassword", password);
			return "login";
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}

	}
}
