package com.cognixia.jump.utility;

import java.util.HashMap;

public class Validate {
	
	// will be used initially to check and compile the errors
	public static HashMap<String, String> check(String name, String username, String password, String balance){
		
		// list of all the errors
		HashMap<String, String> errors = new HashMap<>();
		
		// Both name and username have no rules just make sure they are not blank
		if(name.isBlank()) {
			errors.put("name", "Name is required");
		// all the errors will be blank if there are none
		} else errors.put("name", "");

		if(username.isBlank()) {
			errors.put("username", "Username is required");
		} else errors.put("username", "");
		
		// will use PasswordChecker.java
		if(!PasswordChecker.check(password)) {
			errors.put("password", "something");
		} else errors.put("password", "");
		
		// make sure Float is in proper format
		try {
			Float checked = Float.parseFloat(balance);
			if(checked <= 0.0) {
				errors.put("balance", "Balance must be in the format of 0.00, must be a non-negative number");
			} else {
				errors.put("balance", "");
			}
		}catch(NumberFormatException e) {
			errors.put("balance", "Balance must be in the format of 0.00, must be a number");
		}
		
		return errors;
	}
	
}