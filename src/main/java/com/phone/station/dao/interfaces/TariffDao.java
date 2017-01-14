package com.phone.station.dao.interfaces;

import com.phone.station.entities.Tariff;

public interface TariffDao extends GenericDao<Tariff, Long>{
	Tariff findByTitle(String title);
}