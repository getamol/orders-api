package com.classpath.orders.config;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.context.annotation.Configuration;

import com.classpath.orders.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
class DBHealthEndpoint implements HealthIndicator {

	private final OrderRepository orderRepository;

	@Override
	public Health health() {
		long count = this.orderRepository.count();
		if (count >= 0) {
			return Health.status(Status.UP).withDetail("DB-status", "UP").build();
		}
		return Health.status(Status.DOWN).withDetail("DB-status", "DOWN").build();
	}
}

@Configuration
@RequiredArgsConstructor
class KafkaHealthEndpoint implements HealthIndicator {

	@Override
	public Health health() {
		return Health.status(Status.UP).withDetail("Kafka-status", "UP").build();

	}
}
