package com.phone.station.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.phone.station.dao.interfaces.UserDao;
import com.phone.station.entities.User;
import com.phone.station.entities.enums.Role;
import com.phone.station.service.interfaces.UserService;
import com.phone.station.web.paginator.Paginator;

public class UserServiceImpl implements UserService{
	private static final Logger log = Logger.getLogger(UserServiceImpl.class);

	private static final int NUM_OF_RECORDS_PER_PAGE = 5;

	UserDao userDao;
	Paginator<User> paginator;

	public UserServiceImpl(UserDao userDao) {
		this.userDao = userDao;
		paginator = new Paginator<>(NUM_OF_RECORDS_PER_PAGE);
	}


	@Override
	public boolean create(User user) {
		return userDao.persist(user);
	}

	@Override
	public void update(User user) {
		userDao.update(user);
		log.info("User " + user + " is updated");

	}

	@Override
	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}

	@Override
	public User findById(Long id) {
		return userDao.findByPK(id);
	}

	@Override
	public User findByPhone(String phone) {
		return userDao.findByPhone(phone);
	}

	@Override
	public void changeUserBalance(Long userId, Double cash) {
		User user = userDao.findByPK(userId);
		Double oldBalance = user.getBalance();
		user.setBalance(oldBalance + cash);
		userDao.update(user);
		log.info("User " + user.getUsername() + " balance has changed(old: " + oldBalance +
				 ";new: " + user.getBalance() + ")");
	}

	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}

	@Override
	public List<User> findAllWithRole(Role role) {
		return userDao.findAllWithRole(role);
	}

	@Override
	public List<User> findAllWithRoleAndPageIndex(Role role, int pageIndex) {
		return paginator.findPage(pageIndex, (offset, limit) -> userDao.findAllWithRole(role, offset, limit));
	}

	@Override
	public void delete(User user) {
		userDao.delete(user);
		log.info("User " + user + " is deleted");

	}

	@Override
	public List<User> findByPageIndex(int index) {
		return paginator.findPage(index, (offset, limit) -> userDao.findAll(offset, limit));
	}

	@Override
	public int getNumOfPages() {
		return paginator.getNumOfPages(userDao.getNumOfRecords());
	}

}
