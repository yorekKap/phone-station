package com.phone.station.web.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.phone.station.entities.Payment;
import com.phone.station.entities.Service;
import com.phone.station.entities.User;
import com.phone.station.service.interfaces.PaymentService;
import com.phone.station.service.interfaces.ServicesService;
import com.phone.station.service.interfaces.UserService;
import com.phone.station.utils.CurrentUserFetcher;
import com.phone.station.web.dispatcher.Controller;

public class ServiceController extends Controller{

	private static final String ADDITIONAL_NUMBER_SERVICE = "additional-number";
	private static final String EXCLUSIVE_NUMBER_SERVICE = "exclusive-number";
	private static final String SERVICE_PAYMENT_DESCRIPTION = "Payment for service provided";

	ServicesService servicesService;
	UserService userService;
	PaymentService paymentService;

	public ServiceController(ServicesService servicesService, UserService userService,
							 PaymentService paymentService) {
		this.servicesService = servicesService;
		this.userService = userService;
		this.paymentService = paymentService;
	}

	@Override
	public String get(HttpServletRequest request, HttpServletResponse response) {
		User user = CurrentUserFetcher.getCurrentUser(request);

		request.setAttribute("myServices", servicesService.findAllOfUser(user.getId()));
		request.setAttribute("services", servicesService.findAllNotOfUser(user.getId()));
		request.setAttribute("user", user);

		return "/services";
	}

	@Override
	public String post(HttpServletRequest request, HttpServletResponse response) {
		String action = request.getParameter("action");
		Long serviceId = Long.valueOf(request.getParameter("serviceid"));
		Long userId = CurrentUserFetcher.getCurrentUserId(request);


		if(action != null){
			if(action.equals(ADDITIONAL_NUMBER_SERVICE)){
				setAdditionalNumber(request, response);
			}
			else if(action.equals(EXCLUSIVE_NUMBER_SERVICE)){
				setNewNumber(request, response);
			}
		}

		else{
			servicesService.addServiceToUser(serviceId, userId);
		}

		Service service = servicesService.findById(serviceId);
		paymentService.createPayment(service.getCost() * (-1), service.getTitle() + " service",
									 SERVICE_PAYMENT_DESCRIPTION, userId);
		return null;
	}

	private void setAdditionalNumber(HttpServletRequest request, HttpServletResponse response) {
		User user =  CurrentUserFetcher.getCurrentUser(request);
		String additionalPhone = request.getParameter("number");

		User testUser = userService.findByPhone(additionalPhone);
		if(testUser == null){
			user.setAdditionalPhone(additionalPhone);
			userService.update(user);
		}

		else{
			try {
				response.getOutputStream().write("failed".getBytes());
				response.flushBuffer();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	private void setNewNumber(HttpServletRequest request, HttpServletResponse response) {
		User user =  CurrentUserFetcher.getCurrentUser(request);
		String newPhone = request.getParameter("number");

		User testUser = userService.findByPhone(newPhone);
		if(testUser == null){
			user.setPhone(newPhone);
			userService.update(user);
		}

		else{
			try {
				response.getOutputStream().write("failed".getBytes());
				response.flushBuffer();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
