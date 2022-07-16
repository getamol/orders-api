package com.classpath.orders.dto;

import java.time.LocalDate;

public interface OrderDto {

	String getCustomerName();
	
	String getCustomerEmail();
	
	/*
	 * default double getOrderPrice() { return Math.floor(getOrderPrice()); }
	 */
	
	LocalDate getOrderDate();
}
