package com.phone.station.web.paginator.records;

import java.util.List;

import com.phone.station.entities.User;
import com.phone.station.entities.enums.Role;
import com.phone.station.service.interfaces.UserService;
import com.phone.station.web.paginator.RecordsCollection;

public class UsersWithRoleRecordsCollection implements RecordsCollection<User> {

	private UserService userService;
	private Role role;

	public UsersWithRoleRecordsCollection(UserService userService, Role role) {
		this.userService = userService;
		this.role = role;
	}

	@Override
	public List<User> getRecords(int offset, int limit) {
		return userService.findAllWithRole(role, offset, limit);
	}

	@Override
	public int getNumOfRecords() {
		return userService.getNumOfUsersWithRole(role);
	}

}
