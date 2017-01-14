package com.phone.station.web.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.phone.station.service.interfaces.ServicesService;
import com.phone.station.utils.CurrentUserFetcher;
import com.phone.station.web.dispatcher.Controller;

public class RemoveServiceController extends Controller {

	ServicesService servicesService;

	public RemoveServiceController(ServicesService servicesService) {
		this.servicesService = servicesService;
	}

	@Override
	public String get(HttpServletRequest request, HttpServletResponse response) {
		Long userId = CurrentUserFetcher.getCurrentUserId(request);
		Long serviceId = Long.valueOf(request.getParameter("serviceId"));

		servicesService.removeServiceFromUser(serviceId, userId);

		return null;
	}
}
