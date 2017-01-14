package com.phone.station.service.impl;

import java.util.regex.Pattern;

import com.phone.station.dao.interfaces.UserDao;
import com.phone.station.entities.User;
import com.phone.station.entities.enums.Role;
import com.phone.station.exceptions.service.AuthenticationException;
import com.phone.station.service.interfaces.AuthenticationService;
import com.phone.station.utils.FieldValidator;
import com.phone.station.web.security.UserPrincipal;

public class AuthenticationServiceImpl implements AuthenticationService{

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
			throw new AuthenticationException(AuthenticationException.WRONG_USERNAME);
		}
		if (!user.getPassword().equals(password)) {
			throw new AuthenticationException(AuthenticationException.WRONG_PASSWORD);
		}

		return new UserPrincipal(username, user.getUserRole(), user.getId());

	}



}
