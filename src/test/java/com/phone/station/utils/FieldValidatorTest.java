package com.phone.station.utils;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class FieldValidatorTest {

	@Test
	public void isValidPhoneNumber(){
		String validPhone = "380673255791";

		assertTrue(FieldValidator.isValidPhoneNumber(validPhone));
	}

}
