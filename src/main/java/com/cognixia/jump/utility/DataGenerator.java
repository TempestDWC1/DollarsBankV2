package com.cognixia.jump.utility;

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
}
