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
	private static final String FAIL_PASSWORD = "fpassword";
	private static final String FAIL_USERNAME = "fusername";

	private static final String PASSWORD_PARAMETER = "password";
	private static final String USERNAME_PARAMETER = "username";

	private final static String HOME_URL = "/home";
	private final static String ADMIN_URL = "/admin";

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
		String username = request.getParameter(USERNAME_PARAMETER);
		String password = request.getParameter(PASSWORD_PARAMETER);
		UserPrincipal principal = null;

		try{
			principal = authService.login(username, password);
			request.getSession().setAttribute("principal", principal);
			request.getSession().setMaxInactiveInterval(20 * 60);

			if(principal.getRole() == Role.ADMIN){
				response.sendRedirect(ADMIN_URL);
				log.info("Admin has been logged in");
			}
			else{
				response.sendRedirect(HOME_URL);
				log.info("User: " + username + " has been logged in");
			}
			return null;

		}catch(AuthenticationException e){
			request.setAttribute(FAIL, e.getReason());
			request.setAttribute(FAIL_USERNAME, username);
			request.setAttribute(FAIL_PASSWORD, password);
			log.info("Login failed for username: " + username + ";password: " + password);
			return LOGIN_VIEW;
		} catch (IOException e) {
			log.error("IOException in LoginController:", e);
			throw new RuntimeException(e.getMessage());
		}

	}
}
