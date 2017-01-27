package com.phone.station.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.phone.station.dao.interfaces.PaymentDao;
import com.phone.station.dao.interfaces.UserDao;
import com.phone.station.entities.Payment;
import com.phone.station.entities.User;
import com.phone.station.service.interfaces.PaymentService;
import com.phone.station.web.paginator.Paginator;

public class PaymentServiceImpl implements PaymentService {
	private static final Logger log = Logger.getLogger(PaymentServiceImpl.class);

	private static final int NUM_OF_RECORDS_PER_PAGE = 5;

	PaymentDao paymentDao;
	UserDao userDao;
	Paginator<Payment> paymentsPaginator;

	public PaymentServiceImpl(PaymentDao paymentDao, UserDao userDao) {
		this.paymentDao = paymentDao;
		this.userDao = userDao;
		this.paymentsPaginator = new Paginator<Payment>(NUM_OF_RECORDS_PER_PAGE);
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
	public List<Payment> findByUserIdAndPageIndex(Long userId, int pageIndex) {
		return paymentsPaginator.findPage(pageIndex, (x,y) -> paymentDao.findByUserIdOrderedByDate(userId, x, y));
	}

	@Override
	public int getNumOfPagesWithUserId(Long userId) {
		int numOfRecords = paymentDao.getNumOfRecordsWithUserId(userId);
		return paymentsPaginator.getNumOfPages(numOfRecords);
	}


}
