package com.phone.station.service.interfaces;

import java.util.List;

import com.phone.station.entities.Service;
import com.phone.station.entities.User;

public interface ServicesService {

	Service findById(Long id);
	List<Service> findAll();
	boolean addServiceToUser(Long serviceId, Long userId);
	boolean removeServiceFromUser(Long serviceId, Long userId);
	List<Service> findAllOfUser(Long userId);
	List<Service> findAllNotOfUser(Long userId);
	boolean createService(String title, String description, Double cost);
	void delete(Service service);
	void update(Service service);
}
