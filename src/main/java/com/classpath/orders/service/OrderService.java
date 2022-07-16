package com.classpath.orders.service;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.classpath.orders.model.Order;
import com.classpath.orders.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
	
	private final OrderRepository orderRepository;
	
	
	public Order saveOrder(Order order) {
		return this.orderRepository.save(order);
	}
	
	public Iterable<Order> fetchAllOrders() {
		return this.orderRepository.findAll();
	}
	
	public Order fetchOrderById(long orderId) {
		return this.orderRepository.findById(orderId).orElseThrow(() -> new IllegalArgumentException("invalid order id"));
	}
	
	public void deleteOrderById(long orderId) {
		this.orderRepository.deleteById(orderId);
	}

}
