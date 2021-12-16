package com.cognixia.jump.utility;

import java.util.ArrayList;
import java.util.HashMap;

import com.cognixia.jump.model.Customer;

/*
 * This class is just used for creating testing data for use
 */

public class DataGenerator {

	public static HashMap<String, Customer> testingDataList(){
		HashMap<String, Customer> testCustomers = new HashMap<>();
		testCustomers.put("FooBar", new Customer("Foo", "FooBar", "FooBar@1", (float) 400));
		testCustomers.put("DooBar", new Customer("Doo", "DooBar", "DooBar@2", (float) 100));
		testCustomers.put("MooBar", new Customer("Moo", "MooBar", "MooBar@3", (float) 1000));
		return testCustomers;
	}
	
	public static HashMap<String, ArrayList<String>> testingHistoryList(){
		HashMap<String, ArrayList<String>> testHistory = new HashMap<>();
		testHistory.put("FooBar", new ArrayList<String>());
		testHistory.get("FooBar").add("Initial Balance: 400");
		testHistory.put("DooBar", new ArrayList<String>());
		testHistory.get("DooBar").add("Initial Balance: 100");
		testHistory.put("MooBar", new ArrayList<String>());
		testHistory.get("MooBar").add("Initial Balance: 1000");
		return testHistory;
	}
}
