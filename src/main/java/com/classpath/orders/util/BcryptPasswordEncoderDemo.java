package com.classpath.orders.util;

import java.util.stream.Stream;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BcryptPasswordEncoderDemo {
	
	public static void main(String[] args) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		String rawString = "Welcome";
		
		String encodedPassword1 = passwordEncoder.encode(rawString);
		String encodedPassword2 = passwordEncoder.encode(rawString);
		String encodedPassword3 = passwordEncoder.encode(rawString);
		String encodedPassword4 = passwordEncoder.encode(rawString);
		String encodedPassword5 = passwordEncoder.encode(rawString);
		
		
		Stream.of(encodedPassword1, encodedPassword2, encodedPassword3, encodedPassword4, encodedPassword5)
				.forEach(System.out::println);
		
		// username and password
		// you get username, return user details 
		System.out.println(passwordEncoder.matches(rawString, encodedPassword1));
		
	}

}
