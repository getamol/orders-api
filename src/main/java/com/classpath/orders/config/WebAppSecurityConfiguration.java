package com.classpath.orders.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class WebAppSecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	private final UserDetailsService userDetailsService;
	private final PasswordEncoder passwordEncoder;
	
	
	//authentication
	@Override
	protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder
		    .userDetailsService(this.userDetailsService)
		    .passwordEncoder(this.passwordEncoder);
	}
	
	//authorization
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.cors().disable();
		httpSecurity.csrf().disable();
		httpSecurity.headers().frameOptions().disable();
		httpSecurity.authorizeRequests()
					.antMatchers(HttpMethod.GET, "/api/v1/orders**")
						.hasAnyRole("USER", "ADMIN")
					.antMatchers(HttpMethod.POST, "/api/v1/orders**")
						.hasAnyRole("ADMIN")
					.antMatchers(HttpMethod.DELETE, "/api/v1/orders**")
						.hasAnyRole("ADMIN")
					.anyRequest()
						.authenticated()
					.and()
						.httpBasic()
					.and()
						.sessionManagement()
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
}
