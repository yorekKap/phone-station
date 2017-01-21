package com.phone.station.dao.interfaces;

import java.util.List;


/**
 * The interface that define methods that every dao
 * should implement
 *
 * @author yuri
 *
 * @param <T> entity type that this dao operates on
 * @param <K> primary key type
 */
public interface GenericDao<T, K> {

	boolean persist(T object);

	T findByPK(K id);
	
	List<T> findAll();
	
	void update(T object);
	
	void delete(T object);


}
