package com.phone.station.web.controllers.admin;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.phone.station.entities.Service;
import com.phone.station.service.interfaces.ServicesService;
import com.phone.station.web.dispatcher.Controller;

public class AdminServicesController extends Controller {

	private static final String ADMIN_SERVICES_URL = "admin-services";

	private static final String ACTION_PARAMETER = "action";
	private static final String CREATE_SERVICE = "create-service";
	private static final String DELETE_SERVICE = "delete-service";
	private static final String EDIT_SERVICE = "edit-service";

	private static final String FAIL = "fail";

	ServicesService serviceService;

	public AdminServicesController(ServicesService serviceService) {
		this.serviceService = serviceService;
	}

	@Override
	public String get(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("services", serviceService.findAll());
		return ADMIN_SERVICES_URL;
	}

	@Override
	public String post(HttpServletRequest request, HttpServletResponse response) {
		String action = request.getParameter(ACTION_PARAMETER);

		try{
			if(action.equals(CREATE_SERVICE)){
				createService(request);
			}
			else if(action.equals(DELETE_SERVICE)){
				deleteService(request);
			}
			else if(action.equals(EDIT_SERVICE)){
				editService(request);
			}
			response.sendRedirect("/admin/services");


		}catch(Exception e){
			e.printStackTrace();
			try {
				response.getOutputStream().write(FAIL.getBytes());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

		return null;
	}

	public void createService(HttpServletRequest request){
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		Double cost = Double.valueOf(request.getParameter("cost"));

		serviceService.createService(title, description, cost);
	}

	public void deleteService(HttpServletRequest request){
		Long serviceId = Long.valueOf(request.getParameter("id"));
		serviceService.delete(serviceService.findById(serviceId));
	}

	public void editService(HttpServletRequest request){
		Long id = Long.valueOf(request.getParameter("id"));
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		Double cost = Double.valueOf(request.getParameter("cost"));

		Service service = serviceService.findById(id);
		service.setTitle(title);
		service.setDescription(description);
		service.setCost(cost);

		serviceService.update(service);
	}
}
