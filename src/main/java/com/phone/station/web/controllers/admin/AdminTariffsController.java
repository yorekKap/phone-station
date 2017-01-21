package com.phone.station.web.controllers.admin;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.phone.station.entities.Tariff;
import com.phone.station.service.interfaces.TariffService;
import com.phone.station.web.dispatcher.Controller;

public class AdminTariffsController extends Controller {

	private static final String URL = "/admin/tariffs";

	private static final String TARIFFS_VIEW = "admin-tariffs";

	private static final String FAIL = "fail";

	private static final String ACTION_PARAMETER = "action";

	private static final String EDIT_TARIFF = "edit-tariff";
	private static final String DELETE_TARIFF = "delete-tariff";
	private static final String CREATE_TARIFF = "create-tariff";

	private static final String ID = "id";
	private static final String TITLE = "title";
	private static final String DESCRIPTION = "description";
	private static final String COST_PER_MONTH = "costPerMonth";
	private static final String MINUTES_OF_CALLS_IN_NETWORK = "minutesOfCallsInNetwork";
	private static final String MINUTES_OF_CALLS_OUT_OF_NETWORK = "minutesOfCallsOutOfNetwork";
	private static final String INTERNET_MEGABYTES = "internetMegabytes";

	private TariffService tariffService;

	public AdminTariffsController(TariffService tariffService) {
		this.tariffService = tariffService;
	}

	@Override
	public String get(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("tariffs", tariffService.findAll());
		return TARIFFS_VIEW;
	}

	@Override
	public String post(HttpServletRequest request, HttpServletResponse response) {
		String action = request.getParameter(ACTION_PARAMETER);

		try{
			if(action.equals(CREATE_TARIFF)){
				createTariff(request);
			}
			else if(action.equals(DELETE_TARIFF)){
				deleteTariff(request);
			}
			else if(action.equals(EDIT_TARIFF)){
				editTariff(request);
			}
			response.sendRedirect(URL);


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

	private void createTariff(HttpServletRequest request){
		String title = request.getParameter(TITLE);
		String description = request.getParameter(DESCRIPTION);
		Double costPerMonth = Double.valueOf(request.getParameter(COST_PER_MONTH));
		Integer minutesOfCallsInNetwork = Integer.valueOf(request.getParameter(MINUTES_OF_CALLS_IN_NETWORK));
		Integer minutesOfCallsOutOfNetwork = Integer.valueOf(request.getParameter(MINUTES_OF_CALLS_OUT_OF_NETWORK));
		Integer internetMegabytes = Integer.valueOf(request.getParameter(INTERNET_MEGABYTES));

		Tariff tariff = new Tariff();
		tariff.setTitle(title);
		tariff.setDescription(description);
		tariff.setCostPerMonth(costPerMonth);
		tariff.setMinutesOfCallsInNetwork(minutesOfCallsInNetwork);
		tariff.setMinutesOfCallsOutOfNetwork(minutesOfCallsOutOfNetwork);
		tariff.setInternetMegabytes(internetMegabytes);

		tariffService.create(tariff);
	}

	private void deleteTariff(HttpServletRequest request){
		Long id = Long.valueOf(request.getParameter(ID));
		Tariff tariff = tariffService.findById(id);
		tariffService.delete(tariff);
	}


	private void editTariff(HttpServletRequest request){
		Long id = Long.valueOf(request.getParameter(ID));
		String title = request.getParameter(TITLE);
		String description = request.getParameter(DESCRIPTION);
		Double costPerMonth = Double.valueOf(request.getParameter(COST_PER_MONTH));
		Integer minutesOfCallsInNetwork = Integer.valueOf(request.getParameter(MINUTES_OF_CALLS_IN_NETWORK));
		Integer minutesOfCallsOutOfNetwork = Integer.valueOf(request.getParameter(MINUTES_OF_CALLS_OUT_OF_NETWORK));
		Integer internetMegabytes = Integer.valueOf(request.getParameter(INTERNET_MEGABYTES));

		Tariff tariff = tariffService.findById(id);
		tariff.setTitle(title);
		tariff.setDescription(description);
		tariff.setCostPerMonth(costPerMonth);
		tariff.setMinutesOfCallsInNetwork(minutesOfCallsInNetwork);
		tariff.setMinutesOfCallsOutOfNetwork(minutesOfCallsOutOfNetwork);
		tariff.setInternetMegabytes(internetMegabytes);

		tariffService.update(tariff);
	}

}
