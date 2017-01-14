package com.phone.station.service.interfaces;

import java.util.List;

import com.phone.station.entities.Payment;

public interface PaymentService {

	boolean createPayment(Double price, String title, String description,  Long userId);
	List<Payment> findByUserId(Long userId);
}

