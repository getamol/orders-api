package com.classpath.orders.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnJava;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.system.JavaVersion;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {
	
	@Bean
	User user() {
		return new User();
	}
	
	@ConditionalOnProperty(prefix = "app", name = "loadUser",  havingValue = "false", matchIfMissing = true)
	@Bean
	User userBasedOnPropertyCondition() {
		return new User();
	}
	
	
	@ConditionalOnBean(name = "userBasedOnPropertyCondition")
	@Bean
	User userBasedOnBeanCondition() {
		return new User();
	}
	
	@ConditionalOnMissingBean(name = "userBasedOnPropertyCondition")
	@Bean
	User userBasedOnMissingBeanCondition() {
		return new User();
	}
	
	
	@ConditionalOnJava(value = JavaVersion.EIGHT)
	@Bean
	User userBasedOnJava8() {
		return new User();
	}
	
	
}


class User {
	
}
