package com.phone.station.web.controllers;

import static org.mockito.Mockito.RETURNS_DEEP_STUBS;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.phone.station.entities.User;
import com.phone.station.service.interfaces.MessageService;
import com.phone.station.service.interfaces.UserService;
import com.phone.station.utils.CurrentUserFetcher;
import com.phone.station.web.dispatcher.Controller;

public class MessagesController extends Controller {

	private static final String ADMIN_USERNAME = "Admin";
	private static final String ACTION_PARAMETER = "action";

	private static final String SEND_TO_ADMIN_ACTION = "admin-send";

	MessageService messageService;
	UserService userService;

	public MessagesController(MessageService messageService, UserService userService) {
		this.messageService = messageService;
		this.userService = userService;
	}

	@Override
	public String get(HttpServletRequest request, HttpServletResponse response) {
		Long userId = CurrentUserFetcher.getCurrentUserId(request);
		request.setAttribute("messages", messageService.findByUserId(userId));

		return "/messages";

	}

	@Override
	public String post(HttpServletRequest request, HttpServletResponse response) {
		return null;
	}
}
