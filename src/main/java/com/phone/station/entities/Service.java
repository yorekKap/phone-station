package com.phone.station.entities;

import com.phone.station.dao.annotations.Column;
import com.phone.station.dao.annotations.Id;
import com.phone.station.dao.annotations.Table;

@Table("services")
public class Service implements Identified<Long>{

	@Id
	@Column("id")
	private Long id;

	@Column("title")
	private String title;

	@Column("description")
	private String description;

	@Column("cost")
	private Double cost;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Service() {
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

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}


}
