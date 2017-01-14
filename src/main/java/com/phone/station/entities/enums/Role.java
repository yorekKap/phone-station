package com.phone.station.entities.enums;

public enum Role {
	NONE(0), USER(1), ADMIN(2);

	private Role(int prority){
		this.priority = prority;
	}

	private int priority;

	public int getPriority(){
		return priority;
	}
}
