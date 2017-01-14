package com.phone.station.web.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.phone.station.entities.Payment;
import com.phone.station.service.interfaces.PaymentService;
import com.phone.station.utils.CurrentUserFetcher;
import com.phone.station.web.dispatcher.Controller;

public class PaymentsController extends Controller {

	PaymentService paymentService;

	public PaymentsController(PaymentService paymentService) {
		this.paymentService = paymentService;
	}

	@Override
	public String get(HttpServletRequest request, HttpServletResponse response) {
		Long userId = CurrentUserFetcher.getCurrentUserId(request);
		List<Payment> payments = paymentService.findByUserId(userId);
		request.setAttribute("payments", payments);

		return "payments";
	}
}
