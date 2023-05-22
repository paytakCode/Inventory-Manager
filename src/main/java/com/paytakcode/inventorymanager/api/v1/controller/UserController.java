package com.paytakcode.inventorymanager.api.v1.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paytakcode.inventorymanager.api.v1.data.dto.LoginDto;
import com.paytakcode.inventorymanager.api.v1.data.dto.UserDto;
import com.paytakcode.inventorymanager.api.v1.data.emum.Role;
import com.paytakcode.inventorymanager.api.v1.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * User Controller
 * @Author 김태산
 * @Version 0.1.3
 * @Since 2023-05-18 오후 3:40
 */

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserController {

	private final UserService userService;

	@PostMapping("/user")
	public ResponseEntity<String> userAdd(@RequestBody @Valid UserDto userDto) {
		log.info("[userAdd] param - userDto: {}", userDto.toString());

		String savedUserEmail = userService.addUser(userDto);

		log.info("[userAdd] return - HttpStatus.CREATED(201), savedUserEmail: {}", savedUserEmail);
		return ResponseEntity
			.status(HttpStatus.CREATED)
			.body(savedUserEmail);
	}

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody @Valid LoginDto loginDto, HttpServletRequest request) {
		log.info("[login] param - loginDTO: {}, request: {}", loginDto.toString(), request);

		String loginEmail = userService.login(loginDto, request);

		log.info("[login] return - HttpStatus.OK(200), loginEmail: {}", loginEmail);
		return ResponseEntity
			.status(HttpStatus.OK)
			.body(loginEmail);
	}

	@PutMapping("/user/{email}/role")
	public ResponseEntity<Void> userRoleModify(@PathVariable String email, @RequestBody Role roleName) {
		log.info("[userRoleModify] param - email: {}, role: {}", email, roleName);
		//TODO Required request body is missing 에러 처리해야함
		userService.modifyRole(email, "영업부");

		log.info("[userRoleModify] return - HttpStatus.NO_CONTENT(204)");
		return ResponseEntity
			.status(HttpStatus.NO_CONTENT)
			.build();
	}

}
