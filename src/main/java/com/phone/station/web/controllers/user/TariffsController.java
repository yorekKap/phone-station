package com.phone.station.web.controllers.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.phone.station.entities.Tariff;
import com.phone.station.entities.User;
import com.phone.station.service.interfaces.TariffService;
import com.phone.station.service.interfaces.UserService;
import com.phone.station.utils.CurrentUserFetcher;
import com.phone.station.web.dispatcher.Controller;
import com.phone.station.web.security.UserPrincipal;

public class TariffsController extends Controller {

	private static final String ACTION_PARAMETER = "action";
	private static final String ADD_TARIFF_ACTION = "add-tariff";
	private static final String REMOVE_TARIFF_ACTION = "remove-tariff";

	TariffService tariffService;
	UserService userService;

	public TariffsController(TariffService tariffService, UserService userService) {
		this.tariffService = tariffService;
		this.userService = userService;

	}

	@Override
	public String get(HttpServletRequest request, HttpServletResponse response) {
		List<Tariff> tariffs = tariffService.findAll();
		User user = CurrentUserFetcher.getCurrentUser(request);
		request.setAttribute("currentTariffId", user.getTariffId());
		request.setAttribute("tariffs", tariffs);
		return "tariffs";
	}

	@Override
	public String post(HttpServletRequest request, HttpServletResponse response) {
		String action = request.getParameter(ACTION_PARAMETER);

		if(action.equals(ADD_TARIFF_ACTION)){
			addTariff(request, response);
		}
		else if(action.equals(REMOVE_TARIFF_ACTION)){
			removeTariff(request, response);
		}

		return null;
	}

	private void addTariff(HttpServletRequest request, HttpServletResponse response){
		String username = ((UserPrincipal)request.getSession().getAttribute("principal")).getName();
		User user =  userService.findByUsername(username);
		Long tariffId = Long.valueOf(request.getParameter("id"));
		user.setTariffId(tariffId);
		userService.update(user);
	}

	private void removeTariff(HttpServletRequest request, HttpServletResponse response){
		User user = CurrentUserFetcher.getCurrentUser(request);
		user.setTariffId(null);

		userService.update(user);
	}
}
