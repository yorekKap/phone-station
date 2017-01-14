package com.phone.station.service.impl;

import static org.mockito.Mockito.RETURNS_DEEP_STUBS;

import java.util.List;

import com.phone.station.dao.interfaces.UserDao;
import com.phone.station.entities.User;
import com.phone.station.entities.enums.Role;
import com.phone.station.service.interfaces.UserService;

public class UserServiceImpl implements UserService{

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
	public void addToUserBalance(Long userId, Double cash) {
		User user = userDao.findByPK(userId);
		user.setBalance(user.getBalance() + cash);
		userDao.update(user);
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
	}


}
