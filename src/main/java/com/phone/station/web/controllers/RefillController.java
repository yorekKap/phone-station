package com.phone.station.web.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.phone.station.service.interfaces.PaymentService;
import com.phone.station.service.interfaces.UserService;
import com.phone.station.utils.CurrentUserFetcher;
import com.phone.station.web.dispatcher.Controller;

public class RefillController extends Controller {

	private static final String PAYMENT_TITLE = "Balance refill";
	private static final String PAYMENT_DESCIPRTION = "Balance refill from card: ";

	PaymentService paymentService;

	public RefillController(PaymentService paymentService) {
		this.paymentService = paymentService;
	}

	@Override
	public String get(HttpServletRequest request, HttpServletResponse response) {
		return "refill";
	}

	@Override
	public String post(HttpServletRequest request, HttpServletResponse response) {
		String cardNumber = request.getParameter("cardNumber");
		Double sum = Double.valueOf(request.getParameter("sum"));
		Long userId = CurrentUserFetcher.getCurrentUserId(request);

		paymentService.createPayment(sum, PAYMENT_TITLE, PAYMENT_DESCIPRTION + cardNumber, userId);

		return "refill";
	}
}
