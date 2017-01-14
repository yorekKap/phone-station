package com.phone.station.dao.interfaces;

import java.util.List;

public interface GenericDao<T, K> {

	boolean persist(T object);
	T findByPK(K id);
	List<T> findAll();
	void update(T object);
	void delete(T object);
	
	
}
