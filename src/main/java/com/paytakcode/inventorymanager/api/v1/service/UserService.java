package com.paytakcode.inventorymanager.api.v1.service;

import javax.servlet.http.HttpServletRequest;

import com.paytakcode.inventorymanager.api.v1.data.dto.UserDto;

/**
 * User Service
 * @Author 김태산
 * @Version 0.1.1
 * @Since 2023-05-18 오후 3:43
 */
public interface UserService {
	boolean addUser(UserDto userDto);

	boolean login(String email, String password, HttpServletRequest request);

	boolean modifyRole(String email, String role);
}
