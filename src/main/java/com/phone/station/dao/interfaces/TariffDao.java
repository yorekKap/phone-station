package com.phone.station.dao.interfaces;

import com.phone.station.entities.Service;
import com.phone.station.entities.Tariff;

/**
 * The interface that defines DAO for {@link Tariff} entities
 *
 * @author yuri
 *
 */
public interface TariffDao extends GenericDao<Tariff, Long>{

	Tariff findByTitle(String title);

}