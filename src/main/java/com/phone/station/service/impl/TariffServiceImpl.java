package com.phone.station.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.phone.station.dao.interfaces.TariffDao;
import com.phone.station.entities.Tariff;
import com.phone.station.service.interfaces.TariffService;

public class TariffServiceImpl implements TariffService{
	private static final Logger log = Logger.getLogger(TariffServiceImpl.class);

	TariffDao tariffDao;

	public TariffServiceImpl(TariffDao tariffDao) {
		this.tariffDao = tariffDao;
	}

	@Override
	public boolean create(Tariff tariff) {
		return tariffDao.persist(tariff);
	}

	@Override
	public List<Tariff> findAll() {
		return tariffDao.findAll();
	}

	@Override
	public Tariff findById(Long id) {
		return tariffDao.findByPK(id);
	}

	@Override
	public void delete(Tariff tariff) {
		 tariffDao.delete(tariff);
		 log.info("Tariff " + tariff + " is deleted");

	}

	@Override
	public void update(Tariff tariff) {
		tariffDao.update(tariff);
		log.info("Tariff " + tariff + " is update");

	}

}
