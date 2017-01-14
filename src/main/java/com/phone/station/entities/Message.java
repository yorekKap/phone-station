package com.phone.station.entities;

import java.util.Date;

import com.phone.station.dao.annotations.Column;
import com.phone.station.dao.annotations.Id;
import com.phone.station.dao.annotations.Table;

@Table("messages")
public class Message implements Identified<Long> {

	@Id
	@Column("id")
	private Long id;

	@Column("title")
	private String title;

	@Column("content")
	private String content;

	@Column("sender")
	private String sender;

	@Column("date")
	private Date date;

	@Column("user_id")
	private Long userId;

	public Message() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}
