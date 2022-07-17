package com.classpath.orders.util;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.classpath.orders.model.Role;
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
	private final PasswordEncoder passwordEncoder;
	
	@EventListener(classes = ApplicationReadyEvent.class)
	public void insertUsers(ApplicationReadyEvent readyEvent) {
		log.info("Inserting the users data ");
		//User user = new User(12, 2, true, false, false, "Anand", "Raman");
		User kiran = User.builder().username("kiran").password(passwordEncoder.encode("welcome")).build();
		User vinay = User.builder().username("vinay").password(passwordEncoder.encode("welcome")).build();

		this.userRepository.save(kiran);
		this.userRepository.save(vinay);

		Role userRole = Role.builder().roleName("ROLE_USER").build();
		Role adminRole = Role.builder().roleName("ROLE_ADMIN").build();
		
		this.roleRepository.save(userRole);
		this.roleRepository.save(adminRole);
		
		kiran.addRole(userRole);
		vinay.addRole(userRole);
		vinay.addRole(adminRole);
		
		this.userRepository.save(kiran);
		this.userRepository.save(vinay);
		this.roleRepository.save(userRole);
		this.roleRepository.save(adminRole);

	}

}
