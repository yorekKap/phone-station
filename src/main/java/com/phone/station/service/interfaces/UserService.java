package com.phone.station.service.interfaces;

import java.util.List;

import com.phone.station.entities.User;
import com.phone.station.entities.enums.Role;

public interface UserService {

	boolean create(User user);
	void update(User user);
	User findById(Long id);
	User findByUsername(String username);
	User findByPhone(String phone);
	void addToUserBalance(Long userId, Double cash);
	List<User> findAll();
	List<User> findAllWithRole(Role role);
	void delete(User user);
}
