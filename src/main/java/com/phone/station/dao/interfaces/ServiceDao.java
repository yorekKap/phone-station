package com.phone.station.dao.interfaces;

import java.util.List;

import com.phone.station.entities.Service;
import com.phone.station.entities.User;

public interface ServiceDao extends GenericDao<Service, Long> {

	Service findByTitle(String title);
	List<Service> findWithCostBetween(double start, double end);
	List<Service> findAllOfUser(Long userId);
	List<Service> findAllNotOfUser(Long userId);
	boolean addServiceToUser(Long serviceId, Long userId);
	boolean removeSeviceFromUser(Long serviceId, Long userId);
}
