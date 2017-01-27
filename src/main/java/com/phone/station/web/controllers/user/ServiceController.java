package com.phone.station.web.controllers.user;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.phone.station.entities.Service;
import com.phone.station.entities.User;
import com.phone.station.service.interfaces.PaymentService;
import com.phone.station.service.interfaces.ServicesService;
import com.phone.station.service.interfaces.UserService;
import com.phone.station.utils.CurrentUserFetcher;
import com.phone.station.web.dispatcher.Controller;

public class ServiceController extends Controller{

	private static final String ADDITIONAL_NUMBER_SERVICE_ACTION = "additional-number";
	private static final String EXCLUSIVE_NUMBER_SERVICE_ACTION = "exclusive-number";
	private static final String ADD_SERVICE_ACTION = "add-service";
	private static final String REMOVE_SERVICE_ACTION = "remove-service";
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

		if(action.equals(ADDITIONAL_NUMBER_SERVICE_ACTION)){
			setAdditionalNumber(request, response);
		}
		else if(action.equals(EXCLUSIVE_NUMBER_SERVICE_ACTION)){
			setNewNumber(request, response);
		}
		else if(action.equals(ADD_SERVICE_ACTION)){
			addService(request, response);
		}
		else if(action.equals(REMOVE_SERVICE_ACTION)){
			removeService(request, response);
		}
		return null;
	}

	private void setAdditionalNumber(HttpServletRequest request, HttpServletResponse response) {
		Long serviceId = Long.valueOf(request.getParameter("serviceid"));
		User user =  CurrentUserFetcher.getCurrentUser(request);
		String additionalPhone = request.getParameter("number");

		User testUser = userService.findByPhone(additionalPhone);
		if(testUser == null){
			user.setAdditionalPhone(additionalPhone);
			userService.update(user);
			Service service = servicesService.findById(serviceId);
			paymentService.createPayment(service.getCost() * (-1), service.getTitle() + " service",
									 SERVICE_PAYMENT_DESCRIPTION, user.getId());
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
		Long serviceId = Long.valueOf(request.getParameter("serviceid"));
		User user =  CurrentUserFetcher.getCurrentUser(request);
		String newPhone = request.getParameter("number");

		User testUser = userService.findByPhone(newPhone);
		if(testUser == null){
			user.setPhone(newPhone);
			userService.update(user);
			Service service = servicesService.findById(serviceId);
			paymentService.createPayment(service.getCost() * (-1), service.getTitle() + " service",
									 SERVICE_PAYMENT_DESCRIPTION, user.getId());
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

	private void addService(HttpServletRequest request, HttpServletResponse response){
		Long serviceId = Long.valueOf(request.getParameter("serviceid"));
		Long userId = CurrentUserFetcher.getCurrentUserId(request);

		servicesService.addServiceToUser(serviceId, userId);

		Service service = servicesService.findById(serviceId);
		paymentService.createPayment(service.getCost() * (-1), service.getTitle() + " service",
								 SERVICE_PAYMENT_DESCRIPTION, userId);

	}

	private void removeService(HttpServletRequest request, HttpServletResponse response){
		Long userId = CurrentUserFetcher.getCurrentUserId(request);
		Long serviceId = Long.valueOf(request.getParameter("serviceid"));

		servicesService.removeServiceFromUser(serviceId, userId);

	}

}
