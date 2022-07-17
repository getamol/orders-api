package com.classpath.orders.util;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

import com.classpath.orders.model.User;
import com.classpath.orders.repository.RoleRepository;
import com.classpath.orders.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class BootstrapUserData {
	
	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	
	@EventListener(classes = ApplicationReadyEvent.class)
	public void insertUsers(ApplicationReadyEvent readyEvent) {
		log.info("Inserting the users data ");
		User kiran = User.builder().username("kiran").password("welcome").build();
		User vinay = User.builder().username("vinay").password("welcome").build();
	}

}
