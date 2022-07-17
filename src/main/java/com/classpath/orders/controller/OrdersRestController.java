package com.classpath.orders.controller;

import java.util.Map;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.classpath.orders.model.Order;
import com.classpath.orders.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrdersRestController {
	
	private final OrderService orderService;

	@GetMapping
	@Operation(
			method = "fetch all order",
			description = "API to fetch all the orders in pagination form")
		
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "successfull response for fetching orders"),
		@ApiResponse(responseCode = "401", description = "unauthorized api access"),
		@ApiResponse(responseCode = "403", description = "forbidden"),
	})
	
	public Map<String, Object> fetchAllOrder(
			@RequestParam(name = "page", required = false, defaultValue = "0") int page,
			@RequestParam(name = "size", required = false, defaultValue = "10") int size,
			@RequestParam(name = "sort", required = false, defaultValue = "asc") String direction,
			@RequestParam(name = "field", required = false, defaultValue = "customerName") String property
			){
		return this.orderService.fetchAllOrders(page, size, direction, property);
	}
	
	@GetMapping("/{id}")
	public Order fetchOrderById(@PathVariable("id") long id) {
		return this.orderService.fetchOrderById(id);
	}
	
	@GetMapping("/price")
	public Set<Order> fetchOrderByPrice(
			@RequestParam(name = "min", required = false, defaultValue = "5000") double min,
			@RequestParam(name = "max", required = false, defaultValue = "5500") double max
			) {
		return this.orderService.fetchOrderByPriceRange(min, max);
	}
	
	@GetMapping("/name/{productName}")
	public Set<Order> fetchOrderByPrice(@PathVariable("productName") String productName) {
		return this.orderService.fetchOrderByProductName(productName);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Order saveOrder(@RequestBody @Valid Order order) {
		return this.orderService.saveOrder(order);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteOrderById(@PathVariable("id") long orderId) {
		this.orderService.deleteOrderById(orderId);
	}
	
}
