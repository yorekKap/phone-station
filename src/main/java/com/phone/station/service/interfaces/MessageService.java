package com.phone.station.service.interfaces;

import java.util.List;

import com.phone.station.entities.Message;

public interface MessageService {

	List<Message> findByUserId(Long userId);
	List<Message> findBySender(String sender);
	boolean sendMessage(String title, String content, String sender, Long userId);
}
