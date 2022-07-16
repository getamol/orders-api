package com.classpath.orders.repository;

import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.classpath.orders.dto.OrderDto;
import com.classpath.orders.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{
	
	Set<Order> findByOrderPriceBetween(double min, double max);
	
	//@Query("select o from Order o where o. = ?1")
	Set<Order> findByLineItems_Name(String productName);
	
	Page<OrderDto> findBy(Pageable pageRequest);

}
