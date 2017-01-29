package com.phone.station.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.phone.station.dao.interfaces.PaymentDao;
import com.phone.station.dao.interfaces.UserDao;
import com.phone.station.entities.Payment;
import com.phone.station.entities.User;
import com.phone.station.service.interfaces.PaymentService;

public class PaymentServiceImpl implements PaymentService {
	private static final Logger log = Logger.getLogger(PaymentServiceImpl.class);

	PaymentDao paymentDao;
	UserDao userDao;

	public PaymentServiceImpl(PaymentDao paymentDao, UserDao userDao) {
		this.paymentDao = paymentDao;
		this.userDao = userDao;
	}

	@Override
	public boolean createPayment(Double price, String title, String description, Long userId) {
		User user = userDao.findByPK(userId);
		user.setBalance(user.getBalance() + price);
		userDao.update(user);

		Payment payment = new Payment();
		payment.setPrice(price);
		payment.setTitle(title);
		payment.setDescription(description);
		payment.setUserId(userId);
		payment.setDate(new Date());

		log.info("Payment:" + payment + " is created");
		return paymentDao.persist(payment);
	}

	@Override
	public List<Payment> findByUserId(Long userId) {
		return paymentDao.findByUserIdOrderedByDate(userId);
	}

	@Override
	public List<Payment> findByUserId(Long userId, int offset, int limit) {
		return paymentDao.findByUserIdOrderedByDate(userId, offset, limit);
	}

	@Override
	public int getNumOfPaymentsWithUserId(Long userId) {
		return paymentDao.getNumOfPaymentsWithUserId(userId);
	}
}
