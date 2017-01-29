package com.phone.station.web.paginator.records;

import java.util.List;

import com.phone.station.entities.Payment;
import com.phone.station.service.interfaces.PaymentService;
import com.phone.station.web.paginator.RecordsCollection;

public class PaymentsWithUserIdRecordsCollection implements RecordsCollection<Payment> {

	private PaymentService paymentService;
	private Long userId;

	public PaymentsWithUserIdRecordsCollection(PaymentService paymentService, Long userId) {
		this.paymentService = paymentService;
		this.userId = userId;
	}

	 @Override
	public List<Payment> getRecords(int offset, int limit) {
		 return paymentService.findByUserId(userId, offset, limit);
	}

	 @Override
	public int getNumOfRecords() {
		 return paymentService.getNumOfPaymentsWithUserId(userId);
	}




}
