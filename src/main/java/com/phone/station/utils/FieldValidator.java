package com.phone.station.utils;

public class FieldValidator {

	public static String REGION_NUMBER = "380";
	
	public static boolean isValidPhoneNumber(String phone){
		String regex = REGION_NUMBER + "\\d{9}";
		
		return phone.matches(regex);
	}
}
