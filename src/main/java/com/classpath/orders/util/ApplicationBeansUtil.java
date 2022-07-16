package com.classpath.orders.util;

import static java.util.stream.Stream.of;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationBeansUtil implements CommandLineRunner{

	private final ApplicationContext applicationContext;

	public ApplicationBeansUtil(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}
	
	@Override
	public void run(String... args) throws Exception {
		
		String[] beanNames = this.applicationContext.getBeanDefinitionNames();
		
		//imperative style of coding
		/*
		 * for(String beanName: beanNames) { System.out.println("Bean name:: "+
		 * beanName); }
		 */
		//declarative style of coding
		of(beanNames).forEach(System.out::println);
	}
}