package com.classpath.orders.util;

import java.time.ZoneId;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import javax.sound.sampled.Line;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.classpath.orders.model.LineItem;
import com.classpath.orders.model.Order;
import com.classpath.orders.repository.OrderRepository;
import com.github.javafaker.Faker;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class BootstrapAppData implements ApplicationListener<ApplicationReadyEvent>{
	
	private final OrderRepository orderRepository;
	private final Faker faker = new Faker();
	@Value("${app.totalOrdersCount}")
	private int totalOrdersCount;

	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		log.info("Bootstrapping application data with Orders ");
		IntStream.range(0, totalOrdersCount)
				.forEach(index -> {
					String customerName = faker.name().firstName();
					Order order = Order
									.builder()
										.customerName(customerName)
										.customerEmail(customerName+"@"+faker.internet().domainName())
										.orderDate(faker.date().past(4, TimeUnit.DAYS).toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
									.build();
					
					IntStream.range(0,  faker.number().numberBetween(2, 4))
								.forEach(value -> {
									LineItem lineItem = LineItem.builder()
															.name(faker.commerce().productName())
															.qty(faker.number().numberBetween(2, 5))
															.price(faker.number().randomDouble(2, 400, 800))
															.build();
									order.addLineItem(lineItem);
								});
					double totalOrderPrice = order
												.getLineItems()
												.stream()
												.map(lineItem -> lineItem.getQty() * lineItem.getPrice())
												.reduce(0d, Double::sum);
					order.setOrderPrice(totalOrderPrice);
					//insert Order into Orders table
					this.orderRepository.save(order);
				});
	}
}
