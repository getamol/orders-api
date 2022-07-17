package com.classpath.orders.model;

public class Customer {

	private String firstName;

	public Customer(String fistName, String lastName, int age) {
		this.firstName = fistName;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

}
