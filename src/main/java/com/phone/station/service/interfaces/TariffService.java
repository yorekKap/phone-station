package com.phone.station.service.interfaces;

import java.util.List;

import com.phone.station.entities.Tariff;

public interface TariffService {

	boolean create(Tariff tariff);
	List<Tariff> findAll();
	Tariff findById(Long id);
	void delete(Tariff tariff);
	void update(Tariff tariff);


}
