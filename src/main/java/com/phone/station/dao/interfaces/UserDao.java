package com.phone.station.dao.interfaces;

import java.util.List;

import com.phone.station.entities.User;
import com.phone.station.entities.enums.Role;

/**
 * The interface that defines DAO for {@link User} entities
 *
 * @author yuri
 *
 */
public interface UserDao extends GenericDao<User, Long> {

	User findByPhone(String phone);

	User findByUsername(String username);

	List<User> findAllWithRole(Role role);


}
