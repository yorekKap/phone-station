package com.phone.station.web.controllers.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.phone.station.entities.User;
import com.phone.station.service.interfaces.UserService;
import com.phone.station.utils.CurrentUserFetcher;
import com.phone.station.web.dispatcher.Controller;

public class TariffDisconnectController extends Controller {

	UserService userService;

	public TariffDisconnectController(UserService userService) {
		this.userService = userService;
	}

	@Override
	public String get(HttpServletRequest request, HttpServletResponse response) {
		User user = CurrentUserFetcher.getCurrentUser(request);
		user.setTariffId(null);

		userService.update(user);
		return null;
	}
}
