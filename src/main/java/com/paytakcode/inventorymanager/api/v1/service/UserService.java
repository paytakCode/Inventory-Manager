package com.paytakcode.inventorymanager.api.v1.service;

import com.paytakcode.inventorymanager.api.v1.data.dto.UserDto;

import javax.servlet.http.HttpServletRequest;

/**
 * User Service
 * @Author 김태산
 * @Version 0.1.0
 * @Since 2023-05-18 오후 3:43
 */
public interface UserService {
	UserDto addUser(UserDto userDto);

	boolean login(String email, String password, HttpServletRequest request);
}
