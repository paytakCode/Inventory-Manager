package com.paytakcode.inventorymanager.api.v1.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.paytakcode.inventorymanager.api.v1.handler.CustomAuthenticationFailHandler;
import com.paytakcode.inventorymanager.api.v1.handler.CustomAuthenticationSuccessHandler;

import lombok.RequiredArgsConstructor;

/**
 * Security 설정 파일
 * @Author 김태산
 * @Version 0.1.0
 * @Since 2023-05-17 오후 11:13
 */

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

	private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
	private final CustomAuthenticationFailHandler customAuthenticationFailHandler;
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
			.authorizeRequests()
			.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
			.antMatchers("/signup").anonymous()
			.antMatchers("/intro").permitAll()
			.antMatchers("/admin/**").hasRole("ADMIN")
			.anyRequest().permitAll()

			.and()
			.formLogin()
			.loginPage("/login")
			.loginProcessingUrl("/login")
			.permitAll()
			.successHandler(customAuthenticationSuccessHandler)
			.failureHandler(customAuthenticationFailHandler);

		httpSecurity
			.sessionManagement()
			.invalidSessionUrl("/login")

			.and()
			.logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.invalidateHttpSession(true)
			.deleteCookies("JSESSIONID")
			.permitAll();

		httpSecurity.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());

		return httpSecurity.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder(){
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
}
