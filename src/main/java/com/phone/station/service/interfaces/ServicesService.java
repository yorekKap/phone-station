package com.phone.station.service.interfaces;

import java.util.List;

import com.phone.station.entities.Service;
import com.phone.station.entities.User;



/**
 * Business layer API for convenient support of operations
 * with {@link Service} entity.
 *
 * @author yuri
 *
 */
public interface ServicesService {


	/**
	 * Find service by it's id
	 *
	 * @param id
	 * @return found {@link Service}
	 */
	Service findById(Long id);

	/**
	 * Find all services
	 *
	 * @return {@link List} of all services
	 */
	List<Service> findAll();

	/**
	 * Add {@link Service} to {@link User} by id
	 *
	 * @param serviceId - id of {@link Service} to be added
	 * @param userId - id of {@link User} to whom {@link Service} should be added
	 * @return true if operation was done right, false otherwise
	 */
	boolean addServiceToUser(Long serviceId, Long userId);


	/**
	 * Remove {@link Service} with given id from user
	 *
	 * @param serviceId - id of {@link Service} to be deleted
	 * @param userId - id of {@link User} from whom {@link Service} should be deleted
	 * @return true if operation was done right, false otherwise
	 */
	boolean removeServiceFromUser(Long serviceId, Long userId);

	/**
	 * Find all {@link User} of particular {@link User}
	 *
	 * @param userId - id of {@link User}
	 * @return {@link List} of found {@link User}
	 */
	List<Service> findAllOfUser(Long userId);

	/**
	 * Find all {@link User} not of particular {@link User}
	 *
	 * @param userId - id of {@link User}
	 * @return {@link List} of found {@link User}
	 */
	List<Service> findAllNotOfUser(Long userId);


	/**
	 * Create {@link User} based on given values
	 *
	 * @param title
	 * @param description
	 * @param cost
	 * @return true if operation was done right, false otherwise
	 */
	boolean createService(String title, String description, Double cost);

	/**
	 * Delete {@link Service}
	 *
	 * @param service {@link Service} to delete
	 */
	void delete(Service service);

	/**
	 * Update {@link Service}
	 *
	 * @param service {@link Service} to update
	 */
	void update(Service service);
}
