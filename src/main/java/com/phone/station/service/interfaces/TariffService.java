package com.phone.station.service.interfaces;

import java.util.List;

import com.phone.station.entities.Service;
import com.phone.station.entities.Tariff;

/**
 * Business layer API for convenient support of operations
 * with {@link Tariff} entity.
 *
 * @author yuri
 *
 */
public interface TariffService {


	/**
	 * Create {@link Tariff} object
	 *
	 * @param tariff {@link Tariff} to be created
	 * @return true if operation was done right, false otherwise
	 */
	boolean create(Tariff tariff);


	/**
	 * Find all available {@link Tariff}
	 *
	 * @return {@link List} of found {@link Tariff}
	 */
	List<Tariff> findAll();


	/**
	 * Find {@link Tariff} by given id
	 *
	 * @param id of tariff to be found
	 * @return found {@link Tariff}
	 */
	Tariff findById(Long id);

	/**
	 * Delete {@link Tariff}
	 *
	 * @param Tariff {@link Tariff} to delete
	 */
	void delete(Tariff tariff);

	/**
	 * Update {@link Tariff}
	 *
	 * @param Tariff {@link Tariff} to update
	 */
	void update(Tariff tariff);


}
