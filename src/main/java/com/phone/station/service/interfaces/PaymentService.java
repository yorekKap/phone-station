package com.phone.station.service.interfaces;

import java.util.List;

import com.phone.station.entities.Payment;


/**
 * Business layer API for convenient support of operations
 * with {@link Payment} entity.
 *
 * @author yuri
 *
 */
public interface PaymentService {

	/**
	 * Used for creation of {@link Payment} based on the given values.
	 *
	 * @param price
	 * @param title
	 * @param description
	 * @param userId
	 * @return true if {@link Payment} is created properly, else - false
	 */
	boolean createPayment(Double price, String title, String description,  Long userId);

	List<Payment> findByUserId(Long userId);

	List<Payment> findByUserIdAndPageIndex(Long userId, int pageIndex);

	int getNumOfPagesWithUserId(Long userId);
}

