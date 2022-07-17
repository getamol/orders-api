package com.classpath.orders.service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.classpath.orders.dto.OrderDto;
import com.classpath.orders.model.Order;
import com.classpath.orders.repository.OrderRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
	
	private final OrderRepository orderRepository;
	
	@Transactional
	public Order saveOrder(Order order) {
		if (validate(order)) {
			return this.orderRepository.save(order);
		}
		return null;
	}
	
	private boolean validate(Order order) {
		if(order.getOrderPrice() > 4000) {
			return true;
		}
		return false;
	}

	/**
	 * Return the set of orders in the page
	 * @param page - the page number 
	 * @param size - the number of records per page
	 * @param strDrection - The sort direction (ASC|DESC)
	 * @param property - The property on which the sort is applied
	 * 
	 * @return the response map
	 */
	@Transactional
	public Map<String, Object> fetchAllOrders(int page, int size, String strDirection, String property) {
		
		Sort.Direction direction = strDirection.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
		
		PageRequest pageRequest = PageRequest.of(page, size, direction, property);
		Page<OrderDto> pageResponse = this.orderRepository.findBy(pageRequest);
		
		Map<String, Object> response =  new LinkedHashMap<>();
		response.put("total", pageResponse.getTotalElements());
		response.put("pages", pageResponse.getTotalPages());
		response.put("current", pageResponse.getNumber());
		response.put("data", pageResponse.getContent());

		return response;
	}
	
	@Transactional
	public Order fetchOrderById(long orderId) {
		return this.orderRepository.findById(orderId).orElseThrow(() -> new IllegalArgumentException("invalid order id"));
	}
	
	@Transactional
	public Set<Order> fetchOrderByPriceRange(double min, double max) {
		return this.orderRepository.findByOrderPriceBetween(min, max);
	}
	
	@Transactional
	public Set<Order> fetchOrderByProductName(String productName) {
		return this.orderRepository.findByLineItems_Name(productName);
	}
	
	
	@Transactional
	public void deleteOrderById(long orderId) {
		this.orderRepository.deleteById(orderId);
	}

}
