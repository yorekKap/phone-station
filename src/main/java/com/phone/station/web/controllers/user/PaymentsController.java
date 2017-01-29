package com.phone.station.web.controllers.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.phone.station.entities.Payment;
import com.phone.station.service.interfaces.PaymentService;
import com.phone.station.utils.CurrentUserFetcher;
import com.phone.station.web.dispatcher.Controller;
import com.phone.station.web.paginator.Paginator;
import com.phone.station.web.paginator.records.PaymentsWithUserIdRecordsCollection;

public class PaymentsController extends Controller {

	private static final String PAYMENT_PAGE = "paymentPage";

	private static final int NUM_OR_RECORDS_PER_PAGE = 5;

	private static final String PAGE_INDEX_PARAMETER = "page-index";

	private PaymentService paymentService;
	private Paginator<Payment> paginator;

	public PaymentsController(PaymentService paymentService) {
		this.paymentService = paymentService;
		paginator = new Paginator<>(NUM_OR_RECORDS_PER_PAGE);
	}

	@Override
	public String get(HttpServletRequest request, HttpServletResponse response) {
		Long userId = CurrentUserFetcher.getCurrentUserId(request);
		String pageIndexStr = request.getParameter(PAGE_INDEX_PARAMETER);
		Integer pageIndex = 1;

		if(pageIndexStr != null){
			pageIndex = Integer.valueOf(pageIndexStr);
		}

		paginator.setRecordsCollection(new PaymentsWithUserIdRecordsCollection(paymentService, userId));

		request.setAttribute(PAYMENT_PAGE, paginator.findPage(pageIndex));

		return "payments";
	}
}
