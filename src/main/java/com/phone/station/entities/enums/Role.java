package com.phone.station.entities.enums;

public enum Role {
	ANY(0), NOT_AUTHNENTICATED(1), USER(2), ADMIN(3);

	private Role(int prority){
		this.priority = prority;
	}

	private int priority;

	public int getPriority(){
		return priority;
	}
}
