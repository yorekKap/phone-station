package com.phone.station.entities;

import java.util.Date;

import com.phone.station.dao.annotations.Column;
import com.phone.station.dao.annotations.DateSQL;
import com.phone.station.dao.annotations.Id;
import com.phone.station.dao.annotations.Table;
import com.phone.station.dao.annotations.enums.DateType;

@Table("news")
public class News implements Identified<Long> {

	@Column("id")
	@Id
	private Long id;

	@Column("title")
	private String title;

	@Column("content")
	private String content;

	@Column("date")
	@DateSQL(DateType.DATE_TIME)
	private Date date;

	public News() {
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}


}
