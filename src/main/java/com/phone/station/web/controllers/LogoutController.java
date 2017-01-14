package com.phone.station.web.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.phone.station.web.dispatcher.Controller;

public class LogoutController extends Controller {

	@Override
	public String get(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().removeAttribute("principal");

		try {
			response.sendRedirect("/login");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
