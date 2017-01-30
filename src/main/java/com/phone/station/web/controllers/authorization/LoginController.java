package com.phone.station.web.controllers.authorization;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.phone.station.entities.enums.Role;
import com.phone.station.exceptions.service.AuthenticationException;
import com.phone.station.service.interfaces.AuthenticationService;
import com.phone.station.web.dispatcher.Controller;
import com.phone.station.web.security.UserPrincipal;

public class LoginController extends Controller{
	private static final Logger log = Logger.getLogger(LoginController.class);

	private static final String LOGIN_VIEW = "login";
	private static final String FAIL = "fail";

	AuthenticationService authService;

	public LoginController(AuthenticationService authService) {
		this.authService = authService;
	}

	@Override
	public String get(HttpServletRequest request, HttpServletResponse response) {
		return LOGIN_VIEW;
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
				response.sendRedirect("/admin");
				log.info("Admin has been logged in");
			}
			else{
				response.sendRedirect("/home");
				log.info("User: " + username + " has been logged in");
			}
			return null;

		}catch(AuthenticationException e){
			request.setAttribute(FAIL, e.getReason());
			request.setAttribute("fpassword", username);
			request.setAttribute("fusername", password);
			log.info("Login failed for username: " + username + ";password: " + password);
			return LOGIN_VIEW;
		} catch (IOException e) {
			log.error("IOException in LoginController:", e);
			throw new RuntimeException(e.getMessage());
		}

	}
}
