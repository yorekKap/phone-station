package com.phone.station.service.impl;

import java.util.Date;
import java.util.List;

import com.phone.station.dao.interfaces.MessageDao;
import com.phone.station.entities.Message;
import com.phone.station.service.interfaces.MessageService;

public class MessageServiceImpl implements MessageService{

	private MessageDao messageDao;

	public MessageServiceImpl(MessageDao messageDao) {
		this.messageDao = messageDao;
	}

	@Override
	public List<Message> findByUserId(Long userId) {
		return messageDao.findByUserId(userId);
	}

	@Override
	public List<Message> findBySender(String sender) {
		return messageDao.findBySender(sender);
	}

	@Override
	public boolean sendMessage(String title, String content, String sender, Long userId) {
		Message message = new Message();
		message.setTitle(title);
		message.setContent(content);
		message.setSender(sender);
		message.setDate(new Date());
		message.setUserId(userId);

		return messageDao.persist(message);
	}



}
