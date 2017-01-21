package com.phone.station.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.phone.station.dao.interfaces.UserDao;
import com.phone.station.entities.User;
import com.phone.station.entities.enums.Role;
import com.phone.station.service.interfaces.UserService;

public class UserServiceImpl implements UserService{
	private static final Logger log = Logger.getLogger(UserServiceImpl.class);

	UserDao userDao;

	public UserServiceImpl(UserDao userDao) {
		this.userDao = userDao;
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
	public void delete(User user) {
		userDao.delete(user);
		log.info("User " + user + " is deleted");

	}


}
