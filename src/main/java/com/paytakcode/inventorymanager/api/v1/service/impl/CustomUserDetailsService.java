package com.paytakcode.inventorymanager.api.v1.service.impl;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.paytakcode.inventorymanager.api.v1.data.dto.Role;
import com.paytakcode.inventorymanager.api.v1.data.entity.UserEntity;
import com.paytakcode.inventorymanager.api.v1.data.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * UserDetailsService 구현체
 * @Author 김태산
 * @Version 0.1.0
 * @Since 2023-05-18 오후 2:47
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		log.info("[loadUserByUsername] param - email: {}", email);
		UserEntity foundUser = userRepository.findByEmail(email)
			.orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
		log.info("[loadUserByUsername] result - foundUser: {}", foundUser.toString());
		return new User(foundUser.getEmail(), foundUser.getPassword(), getAuthorities(foundUser.getRole()));
	}

	private Collection<? extends GrantedAuthority> getAuthorities(Role role) {
		log.info("[getAuthorities] param - role {}", role.toString());
		return Collections.singleton(new SimpleGrantedAuthority(role.name()));
	}

}
