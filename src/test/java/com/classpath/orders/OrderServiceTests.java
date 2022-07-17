package com.classpath.orders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.classpath.orders.model.Order;
import com.classpath.orders.repository.OrderRepository;
import com.classpath.orders.service.OrderService;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTests {
	
	@Mock
	private OrderRepository orderRepository;
	
	@InjectMocks
	private OrderService orderService;
	
	
	@Test
	void testSaveOrder() {
		//set the expectations
		Order order = Order.builder().id(12L).customerEmail("vinay@gmail.com").orderPrice(5000).customerName("vinay").orderDate(LocalDate.now()).build();
		when(this.orderRepository.save(any(Order.class))).thenReturn(order);
		
		//execute
		Order inputOrder = Order.builder().customerEmail("vinay@gmail.com").orderPrice(5000).customerName("vinay").orderDate(LocalDate.now()).build();
		Order outputOrder = this.orderService.saveOrder(inputOrder);
		
		//assertions
		Assertions.assertNotNull(outputOrder);
		Assertions.assertEquals(outputOrder.getId(), order.getId());
		
		//verification
		verify(this.orderRepository, times(1)).save(inputOrder);
	}
	
	@Test
	void testInvalidSaveOrder() {
		//set the expectations
		Order order = Order.builder().id(12L).customerEmail("vinay@gmail.com").orderPrice(3000).customerName("vinay").orderDate(LocalDate.now()).build();
		lenient().when(this.orderRepository.save(any(Order.class))).thenReturn(order);
		
		//execute
		Order inputOrder = Order.builder().customerEmail("vinay@gmail.com").orderPrice(3000).customerName("vinay").orderDate(LocalDate.now()).build();
		Order outputOrder = this.orderService.saveOrder(inputOrder);
		
		//assertions
		Assertions.assertNull(outputOrder);
		
		//verification
		verify(this.orderRepository, never()).save(inputOrder);
	}


	
	

}
