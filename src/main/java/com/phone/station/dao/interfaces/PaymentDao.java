package com.phone.station.dao.interfaces;

import java.util.List;

import com.phone.station.entities.Payment;

/**
 * The interface that defines DAO for {@link Payment} entities
 *
 * @author yuri
 *
 */
public interface PaymentDao extends GenericDao<Payment, Long>{

	List<Payment> findByUserId(Long userId);

	List<Payment> findByUserIdOrderedByDate(Long userId);

	List<Payment> findByUserIdOrderedByDate(Long userId, int offset, int limit);

	List<Payment> findAllOrderedByDate();

	int getNumOfPaymentsWithUserId(Long userId);


}
