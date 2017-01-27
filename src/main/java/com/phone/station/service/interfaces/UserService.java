package com.phone.station.service.interfaces;

import java.util.List;

import com.phone.station.entities.Service;
import com.phone.station.entities.User;
import com.phone.station.entities.enums.Role;

/**
 * Business layer API for convenient support of operations
 * with {@link User} entity.
 *
 * @author yuri
 *
 */
 /**
 * @author yuri
 *
 */
public interface UserService {


	/**
	 * Create {@link User} object
	 *
	 * @param User {@link User} to be created
	 * @return true if operation was done right, false otherwise
	 */
	boolean create(User user);


	/**
	 * Find {@link User} by given id
	 *
	 * @param id of {@link User} to be found
	 * @return found {@link User}
	 */
	User findById(Long id);

	/**
	 * Find {@link User} by given username
	 *
	 * @param username of {@link User} to be found
	 * @return found {@link User}
	 */
	User findByUsername(String username);

	/**
	 * Find {@link User} by given phone
	 *
	 * @param phone of {@link User} to be found
	 * @return found {@link User}
	 */
	User findByPhone(String phone);

	/**
	 * Increase of subside {@link User} balance
	 *
	 * @param userId - id of user whose balance should be changed
	 * @param cash - amount of cash to add(if positive) or remove(if negative) from {@link User}
	 */
	void changeUserBalance(Long userId, Double cash);


	/**
	 * Find all {@link User}
	 *
	 * @return {@link List} of {@link User}
	 */
	List<User> findAll();

	/**
	 * Find all {@link User} of the particular {@link Role}
	 *
	 * @return {@link List} of {@link User}
	 */

	List<User> findAllWithRole(Role role);

	/**
	 * Find @{@code limit} {@link User}s of the particular {@link Role}
	 * starting from {@code offset}
	 *
	 * @param role
	 * @param offset
	 * @param limit
	 * @return {@link List} of {@link User}
	 */
	List<User> findAllWithRoleAndPageIndex(Role role, int pageIndex);

	/**
	 * Update {@link User}
	 *
	 * @param User {@link User} to update
	 */
	void update(User user);

	/**
	 * Delete {@link User}
	 *
	 * @param User {@link Delete} to update
	 */
	void delete(User user);

	/**
	 * Find {@link User} from given page
	 *
	 * @param index - index of page
	 *
	 * @return {@link List} of {@link User}
	 */
	List<User> findByPageIndex(int index);

	/**
	 * @return number of pages
	 */
	int getNumOfPages();
}
