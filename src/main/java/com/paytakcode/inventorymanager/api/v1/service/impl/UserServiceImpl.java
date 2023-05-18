package com.paytakcode.inventorymanager.api.v1.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.paytakcode.inventorymanager.api.v1.data.dao.UserDao;
import com.paytakcode.inventorymanager.api.v1.data.dto.RegisterDto;
import com.paytakcode.inventorymanager.api.v1.data.dto.Role;
import com.paytakcode.inventorymanager.api.v1.data.dto.UserDto;
import com.paytakcode.inventorymanager.api.v1.data.entity.UserEntity;
import com.paytakcode.inventorymanager.api.v1.service.UserService;

import lombok.RequiredArgsConstructor;

/**
 * User Service 구현체
 * @Author 김태산
 * @Version 0.1.0
 * @Since 2023-05-18 오후 3:43
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserDao userDao;
	private final PasswordEncoder passwordEncoder;

	@Override
	public UserDto addUser(RegisterDto registerDto) {
		UserEntity user = userDao.saveUser(UserEntity.builder()
			.email(registerDto.getEmail())
			.name(registerDto.getName())
			.password(passwordEncoder.encode(registerDto.getPassword()))
			.role(Role.ROLE_USER)
			.build());

		return UserDto.builder()
			.email(user.getEmail())
			.name(user.getName())
			.password(user.getPassword())
			.role(user.getRole())
			.build();
	}

}
