package com.phone.station.service.impl;

import org.apache.log4j.Logger;

import com.phone.station.dao.interfaces.UserDao;
import com.phone.station.entities.User;
import com.phone.station.exceptions.service.AuthenticationException;
import com.phone.station.service.interfaces.AuthenticationService;
import com.phone.station.utils.FieldValidator;
import com.phone.station.web.security.UserPrincipal;

public class AuthenticationServiceImpl implements AuthenticationService{
	private static final Logger log = Logger.getLogger(AuthenticationServiceImpl.class);

	UserDao userDao;

	public AuthenticationServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public UserPrincipal login(String username, String password)throws AuthenticationException {
		User user = null;

		user = FieldValidator.isValidPhoneNumber(username) ? userDao.findByPhone(username) :
															 userDao.findByUsername(username);

		if (user == null) {
			log.warn("Login failed: wrong username");
			throw new AuthenticationException(AuthenticationException.WRONG_USERNAME);
		}
		if (!user.getPassword().equals(password)) {
			log.warn("Login failed: wrong password");
			throw new AuthenticationException(AuthenticationException.WRONG_PASSWORD);
		}

		log.info("UserPricipal with username: " + username + ";password: " + password + " is created");
		return new UserPrincipal(username, user.getUserRole(), user.getId());

	}



}
