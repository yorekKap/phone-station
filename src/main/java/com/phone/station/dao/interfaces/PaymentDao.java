package com.phone.station.dao.interfaces;

import java.util.List;

import com.phone.station.entities.Payment;

public interface PaymentDao extends GenericDao<Payment, Long>{

	List<Payment> findByUserId(Long userId);
	List<Payment> findByUserIdOrderedByDate(Long userId);
	List<Payment> findAllOrderedByDate();
}
