package com.phone.station.service.impl;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import com.phone.station.dao.interfaces.PaymentDao;
import com.phone.station.dao.interfaces.UserDao;
import com.phone.station.entities.Payment;
import com.phone.station.entities.User;
import com.phone.station.service.interfaces.PaymentService;

public class PaymentServiceImpl implements PaymentService {

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

		return paymentDao.persist(payment);
	}

	@Override
	public List<Payment> findByUserId(Long userId) {
		return paymentDao.findByUserIdOrderedByDate(userId);
	}


}
