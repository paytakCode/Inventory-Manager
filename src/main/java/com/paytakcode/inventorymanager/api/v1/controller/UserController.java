package com.paytakcode.inventorymanager.api.v1.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paytakcode.inventorymanager.api.v1.data.dto.UserDto;
import com.paytakcode.inventorymanager.api.v1.service.impl.UserServiceImpl;

import lombok.RequiredArgsConstructor;

/**
 * User 인증 관련 Controller
 * @Author 김태산
 * @Version 0.1.0
 * @Since 2023-05-17 오후 11:47
 */

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserController {

	private final UserServiceImpl userService;

	@PostMapping("/login")
	public ResponseEntity<String> login(){
		return ResponseEntity.ok("Success");
	}

	@PostMapping("/user")
	public ResponseEntity<UserDto> userAdd(){
		return ResponseEntity.ok(userService.addUser(new UserDto()));
	}
}
