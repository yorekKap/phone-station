package com.phone.station.web.controllers.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.phone.station.entities.Payment;
import com.phone.station.service.interfaces.PaymentService;
import com.phone.station.utils.CurrentUserFetcher;
import com.phone.station.web.dispatcher.Controller;

public class PaymentsController extends Controller {

	private static final String PAGE_INDEX_PARAMETER = "page-index";

	PaymentService paymentService;

	public PaymentsController(PaymentService paymentService) {
		this.paymentService = paymentService;
	}

	@Override
	public String get(HttpServletRequest request, HttpServletResponse response) {
		Long userId = CurrentUserFetcher.getCurrentUserId(request);
		String pageIndexStr = request.getParameter(PAGE_INDEX_PARAMETER);
		Integer pageIndex = 1;

		if(pageIndexStr != null){
			pageIndex = Integer.valueOf(pageIndexStr);
		}

		List<Payment> payments = paymentService.findByUserIdAndPageIndex(userId, pageIndex);
		int numOfPages = paymentService.getNumOfPagesWithUserId(userId);

		request.setAttribute("payments", payments);
		request.setAttribute("pageIndex", pageIndex);
		request.setAttribute("numOfPages", numOfPages);

		return "payments";
	}
}
