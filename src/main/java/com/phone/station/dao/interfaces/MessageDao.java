package com.phone.station.dao.interfaces;

import java.util.List;

import com.phone.station.entities.Message;

public interface MessageDao extends GenericDao<Message, Long> {

	List<Message> findByUserId(Long userId);
	List<Message> findBySender(String sender);


}
