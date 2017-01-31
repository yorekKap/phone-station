package com.phone.station.web.controllers.admin;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.phone.station.entities.User;
import com.phone.station.entities.enums.Role;
import com.phone.station.service.interfaces.UserService;
import com.phone.station.utils.PageIndexFetcher;
import com.phone.station.web.dispatcher.Controller;
import com.phone.station.web.paginator.Paginator;
import com.phone.station.web.paginator.records.UsersWithRoleRecordsCollection;

public class AdminUsersController extends Controller{

	private static final String ACTION_PARAMETER = "action";
	private static final String DISCONNECT_USER = "disconnect-user";
	private static final String FAIL = "fail";

	private static final int NUM_OF_RECORDS_PER_PAGE = 10;

	UserService userService;
	Paginator<User> paginator;

	public AdminUsersController(UserService userService) {
		this.userService = userService;
		paginator = new Paginator<>(NUM_OF_RECORDS_PER_PAGE);
	}

	@Override
	public String get(HttpServletRequest request, HttpServletResponse response) {
		int pageIndex = PageIndexFetcher.getPageIndex(request);
		paginator.setRecordsCollection(new UsersWithRoleRecordsCollection(userService, Role.USER));

		request.setAttribute("userPage", paginator.findPage(pageIndex));

		return "admin-users";

	}

	@Override
	public String post(HttpServletRequest request, HttpServletResponse response) {
		String action = request.getParameter(ACTION_PARAMETER);

		if(action != null){
			if(action.equals(DISCONNECT_USER)){
				disconnectUser(request, response);
			}

			return null;

		}
		else{
			try {
				response.getOutputStream().write(FAIL.getBytes());
				response.flushBuffer();
				return null;
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}
	}

	private void disconnectUser(HttpServletRequest request, HttpServletResponse response){
		try{
			Long userId = Long.valueOf(request.getParameter("userId"));
			User user = userService.findById(userId);
			userService.delete(user);
		}catch (NumberFormatException e) {
			try {
				response.getOutputStream().write(FAIL.getBytes());
				response.flushBuffer();
				e.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		}

}
