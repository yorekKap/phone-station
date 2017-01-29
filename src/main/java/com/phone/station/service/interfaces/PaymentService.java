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
public interface PaymentService{

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

	/**
	 * Find {@link Payment} of a particular {@link User} by {@code userId}
	 *
	 * @param userId - id of needed user
	 * @return {@link List} of {@link Payment}s
	 */
	List<Payment> findByUserId(Long userId);

	/**
	 * Find  {@code limit} of {@link Payment}s of a particular {@link User} by {@code userId}
	 * from {@code offset}
	 *
	 * @param userId
	 * @param offset
	 * @param limit
	 * @return {@link List} of {@link Payment}s
	 */
	List<Payment> findByUserId(Long userId, int offset, int limit);


	/**
	 * @return number of {@link Payment}s with particular {@code userId}
	 */
	int getNumOfPaymentsWithUserId(Long userId);
}

