package com.paytakcode.inventorymanager.api.v1.service;

import javax.servlet.http.HttpServletRequest;

import com.paytakcode.inventorymanager.api.v1.data.dto.LoginDto;
import com.paytakcode.inventorymanager.api.v1.data.dto.UserDto;
import com.paytakcode.inventorymanager.api.v1.data.dto.UserInfoDto;
import com.paytakcode.inventorymanager.api.v1.data.emum.Role;

/**
 * User Service
 * @Author 김태산
 * @Version 0.2.0
 * @Since 2023-05-18 오후 3:43
 */
public interface UserService {
	UserInfoDto addUser(UserDto userDto);

	String login(LoginDto loginDto);

	void updateUserRole(Long userId, Role role);

	void logout(HttpServletRequest request);
}
