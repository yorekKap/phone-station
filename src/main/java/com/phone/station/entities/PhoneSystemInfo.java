package com.phone.station.entities;

public class PhoneSystemInfo implements Identified<Long>{

	private Long id;
	private String title;
	private String code;

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getCode() {
		return code;
	}
	
	public String getTitle() {
		return title;
	}
	
	
}
