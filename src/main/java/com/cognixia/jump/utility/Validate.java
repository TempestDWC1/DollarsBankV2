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
			// this well be used to determine if the whole map has
			// an error or not
			errors.put("error", "error");
		// all the errors will be blank if there are none
		} else errors.put("name", "");

		if(username.isBlank()) {
			errors.put("username", "Username is required");
			errors.put("error", "error");
		} else errors.put("username", "");
		
		// will use PasswordChecker.java
		if(!PasswordChecker.check(password)) {
			errors.put("password", "something");
			errors.put("error", "error");
		} else errors.put("password", "");
		
		// make sure Float is in proper format
		try {
			Float checked = Float.parseFloat(balance);
			if(checked <= 0.0) {
				errors.put("balance", "Balance must be in the format of 0.00, must be a non-negative number");
				errors.put("error", "error");
			} else {
				errors.put("balance", "");
			}
		}catch(NumberFormatException e) {
			errors.put("balance", "Balance must be in the format of 0.00, must be a number");
			errors.put("error", "error");
		}
		
		return errors;
	}
	
	// will be used by Deposit, Withdraw, and Transfer to check input
	public static Boolean checkInput(String input) {
		try {
			// if you can successful parse a float then true
			Float fl = Float.parseFloat(input);
			// also quickly check if the number is negative
			if(fl < 0) throw new Exception();
			// if a float and not negative then good to go
			return true;
		}catch(Exception e) {
			// if you can't then false
			return false;
		}
	}
	
}
