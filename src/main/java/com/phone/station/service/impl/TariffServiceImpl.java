package com.phone.station.service.impl;

import java.util.List;

import com.phone.station.dao.interfaces.TariffDao;
import com.phone.station.entities.Tariff;
import com.phone.station.service.interfaces.TariffService;

public class TariffServiceImpl implements TariffService{

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
	}

	@Override
	public void update(Tariff tariff) {
		tariffDao.update(tariff);
	}

}
