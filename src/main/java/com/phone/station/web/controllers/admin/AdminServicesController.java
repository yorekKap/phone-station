package com.phone.station.web.controllers.admin;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.phone.station.entities.Service;
import com.phone.station.service.interfaces.ServicesService;
import com.phone.station.web.dispatcher.Controller;
import com.phone.station.web.paginator.PageInfo;
import com.phone.station.web.paginator.Paginator;
import com.phone.station.web.paginator.records.ServicesRecordsCollection;

public class AdminServicesController extends Controller {

	private static final String ACTION_PARAMETER = "action";
	private static final String FAIL = "fail";

	private static final String PAGE_INDEX = "page-index";
	private static final int NUM_OF_RECORDS_PER_PAGE = 5;

	private ServicesService serviceService;
	private Paginator<Service> paginator;

	public AdminServicesController(ServicesService serviceService) {
		this.serviceService = serviceService;
		paginator = new Paginator<>(NUM_OF_RECORDS_PER_PAGE, new ServicesRecordsCollection(serviceService));
	}

	@Override
	public String get(HttpServletRequest request, HttpServletResponse response) {
		String pageIndexStr = request.getParameter(PAGE_INDEX);
		int pageIndex = 1;
		if(pageIndexStr != null){
			pageIndex = Integer.valueOf(pageIndexStr);
		}

		PageInfo<Service> page = paginator.findPage(pageIndex);

		request.setAttribute("servicePage", page);
		return "admin-services";
	}

	@Override
	public String post(HttpServletRequest request, HttpServletResponse response) {
		String action = request.getParameter(ACTION_PARAMETER);

		try{
			if(action.equals("create-service")){
				createService(request);
			}
			else if(action.equals("edit-service")){
				deleteService(request);
			}
			else if(action.equals("remove-service")){
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
