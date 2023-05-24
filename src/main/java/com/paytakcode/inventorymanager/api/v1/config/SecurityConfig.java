package com.paytakcode.inventorymanager.api.v1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

/**
 * Security Config
 * Security 세팅은 버전별로 매우 상이함
 * @Author 김태산
 * @Version 0.2.0
 * @Since 2023-05-18 오후 2:45
 */

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private final JwtFilter jwtFilter;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.httpBasic().disable()
			.csrf().disable()
			.cors().and()
			.authorizeRequests()
			.antMatchers(HttpMethod.GET, "/api/v1/login", "/api/v1/intro", "/api/v1/register").permitAll()
			.antMatchers(HttpMethod.POST, "/api/v1/users", "/api/v1/login").permitAll()
			.antMatchers("/api/v1/admin/**").hasRole("ADMIN")
			.antMatchers("/api/v1/production/**").hasAnyRole("PRODUCTION", "ADMIN")
			.antMatchers("/api/v1/sales/**").hasAnyRole("SALES", "ADMIN")
			.antMatchers("/api/v1/material/**").hasAnyRole("MATERIAL", "ADMIN")
			.anyRequest().authenticated()
			.and()
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.formLogin()
			.loginPage("/api/v1/login")
			.loginProcessingUrl("/api/v1/loginProc")
			.defaultSuccessUrl("/api/v1/main")
			.and()
			.logout()
			.logoutUrl("/api/v1/logout")
			.logoutSuccessUrl("/api/v1/intro")
			.and()
			.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws
		Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
