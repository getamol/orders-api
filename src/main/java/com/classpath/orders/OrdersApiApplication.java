package com.classpath.orders;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(
		title = "Orders REST API",
		description = "API for Orders Restfull Operations",
		contact = @Contact(
				email = "developer@classpath.io",
				name = "Developes"
				),
		termsOfService = "support@classpath.io",
		version = "1.0.0"
		
		))
public class OrdersApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrdersApiApplication.class, args);
	}

}
