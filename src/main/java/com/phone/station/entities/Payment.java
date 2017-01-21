package com.phone.station.entities;

import java.util.Date;

import com.phone.station.dao.annotations.Column;
import com.phone.station.dao.annotations.DateSQL;
import com.phone.station.dao.annotations.Id;
import com.phone.station.dao.annotations.Table;
import com.phone.station.dao.annotations.enums.DateType;

@Table("payments")
public class Payment implements Identified<Long>{

	@Id
	@Column("id")
	private Long id;

	@Column("title")
	private String title;

	@Column("description")
	private String description;

	@Column("price")
	private Double price;

	@DateSQL(DateType.DATE_TIME)
	@Column("date")
	private Date date;

	@Column("user_id")
	private Long userId;

	public Payment() {
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
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

	@Override
	public String toString() {
		return "Payment [id=" + id + ", title=" + title + ", description=" + description + ", price=" + price
				+ ", date=" + date + ", userId=" + userId + "]";
	}



}
