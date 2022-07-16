package com.classpath.orders.controller;

import java.util.Set;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.classpath.orders.model.Order;
import com.classpath.orders.service.OrderService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrdersRestController {
	
	private final OrderService orderService;

	@GetMapping
	public Set<Order> fetchAllOrder(){
		return this.orderService.fetchAllOrders();
	}
	
	@GetMapping("/{id}")
	public Order fetchOrderById(@PathVariable("id") long id) {
		return this.orderService.fetchOrderById(id);
	}
	
	@PostMapping
	public Order saveOrder(@RequestBody Order order) {
		return this.orderService.saveOrder(order);
	}
	
	@DeleteMapping("/{id}")
	public void deleteOrderById(@PathVariable("id") long orderId) {
		this.orderService.deleteOrderById(orderId);
	}
	
}
