package com.phone.station.web.controllers;

import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.phone.station.web.dispatcher.Controller;

public class WelcomeController extends Controller {
	
	@Override
	public String get(HttpServletRequest request, HttpServletResponse response) {
		return "welcome";
	}
	
}
