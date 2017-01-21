package com.phone.station.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.phone.station.dao.interfaces.ServiceDao;
import com.phone.station.entities.Service;
import com.phone.station.service.interfaces.ServicesService;

public class ServicesServiceImpl implements ServicesService{
	private static final Logger log = Logger.getLogger(ServicesServiceImpl.class);

	ServiceDao serviceDao;

	public ServicesServiceImpl(ServiceDao serviceDao) {
		this.serviceDao = serviceDao;
	}

	@Override
	public Service findById(Long id) {
		return serviceDao.findByPK(id);
	}


	@Override
	public List<Service> findAll() {
		return serviceDao.findAll();
	}

	@Override
	public List<Service> findAllOfUser(Long userId) {
		return serviceDao.findAllOfUser(userId);
	}

	@Override
	public List<Service> findAllNotOfUser(Long userId) {
		return serviceDao.findAllNotOfUser(userId);
	}


	@Override
	public boolean addServiceToUser(Long serviceId, Long userId) {
		return serviceDao.addServiceToUser(serviceId, userId);
	}


	@Override
	public boolean removeServiceFromUser(Long serviceId, Long userId) {
		return serviceDao.removeSeviceFromUser(serviceId, userId);
	}

	@Override
	public boolean createService(String title, String description, Double cost) {
		Service service = new Service();
		service.setTitle(title);
		service.setDescription(description);
		service.setCost(cost);

		log.info("Service: " + service + " is created");
		return serviceDao.persist(service);
	}

	@Override
	public void delete(Service service) {
		serviceDao.delete(service);
		log.info("Service " + service + " is deleted");
	}

	@Override
	public void update(Service service) {
		serviceDao.update(service);
		log.info("Service " + service + " is updated");

	}

}
