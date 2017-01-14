package com.phone.station.entities;

import com.phone.station.dao.annotations.Column;
import com.phone.station.dao.annotations.Id;
import com.phone.station.dao.annotations.Table;

@Table("tariffs")
public class Tariff implements Identified<Long> {

	@Id
	@Column("id")
	private Long id;

	@Column("title")
	private String title;

	@Column("description")
	private String description;

	@Column("cost_per_month")
	private Double costPerMonth;

	@Column("minutes_of_calls_in_network")
	private Integer minutesOfCallsInNetwork;

	@Column("minutes_of_calls_out_of_network")
	private Integer minutesOfCallsOutOfNetwork;

	@Column("internet_mb")
	private Integer internetMegabytes;

	public Tariff() {
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

	public double getCostPerMonth() {
		return costPerMonth;
	}

	public void setCostPerMonth(double costPerMonth) {
		this.costPerMonth = costPerMonth;
	}

	public int getMinutesOfCallsInNetwork() {
		return minutesOfCallsInNetwork;
	}

	public void setMinutesOfCallsInNetwork(int minutesOfCallsInNetwork) {
		this.minutesOfCallsInNetwork = minutesOfCallsInNetwork;
	}

	public int getMinutesOfCallsOutOfNetwork() {
		return minutesOfCallsOutOfNetwork;
	}

	public void setMinutesOfCallsOutOfNetwork(int minutesOfCallsOutOfNetwork) {
		this.minutesOfCallsOutOfNetwork = minutesOfCallsOutOfNetwork;
	}

	public int getInternetMegabytes() {
		return internetMegabytes;
	}

	public void setInternetMegabytes(int internetMegabytes) {
		this.internetMegabytes = internetMegabytes;
	}



}
