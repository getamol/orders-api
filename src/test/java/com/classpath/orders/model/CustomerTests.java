package com.classpath.orders.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CustomerTests {
	
	@Test
	void createCustomer() {
		Customer customer = new Customer("Mohan", "Kumar", 22);
		Assertions.assertNotNull(customer);
	}
	
	@Test
	void testGetters() {
		Customer customer = new Customer("Mohan", "Kumar", 22);
		String firstName = customer.getFirstName();
		Assertions.assertEquals("Mohan", firstName);
	}
	
	@Test
	void testSetters() {
		Customer customer = new Customer("Mohan", "Kumar", 22);
		customer.setFirstName("Vinod");
		String firstName = customer.getFirstName();
		Assertions.assertEquals("Vinod", firstName);
	}

}
