package com.paytakcode.inventorymanager.api.v1.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.paytakcode.inventorymanager.api.v1.data.dao.UserDao;
import com.paytakcode.inventorymanager.api.v1.data.dto.UserDto;
import com.paytakcode.inventorymanager.api.v1.data.entity.User;

import lombok.RequiredArgsConstructor;

/**
 * User 인증 관련 Service Impl
 * @Author 김태산
 * @Version 0.1.0
 * @Since 2023-05-18 오전 12:15
 */

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService {

	private final UserDao userDao;

	public UserDto addUser(UserDto userDto){
		User user = new User();
		user.setEmail("test@mail.com");
		user.setPassword("test");
		User savedUser = userDao.saveUser(user);
		UserDto savedDto = new UserDto();
		savedDto.setEmail(savedUser.getEmail());
		savedDto.setPassword(savedUser.getPassword());
		return savedDto;
	}
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return null;
	}
}
