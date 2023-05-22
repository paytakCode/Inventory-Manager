package com.paytakcode.inventorymanager.api.v1.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paytakcode.inventorymanager.api.v1.data.dto.UserDto;
import com.paytakcode.inventorymanager.api.v1.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * User Controller
 * @Author 김태산
 * @Version 0.1.2
 * @Since 2023-05-18 오후 3:40
 */

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserController {

	private final UserService userService;

	@PostMapping("/login")
	public ResponseEntity<Void> login(@RequestBody @Valid UserDto userDto, HttpServletRequest request) {

		log.info("[login] param - email: {}, password: {}, request: {}", userDto.getEmail(), userDto.getPassword(),
			request);

		boolean result = userService.login(userDto.getEmail(), userDto.getPassword(), request);

		HttpStatus httpStatus = null;

		if (result) {
			httpStatus = HttpStatus.OK;
		} else {
			httpStatus = HttpStatus.UNAUTHORIZED;
		}

		log.info("[login] return - HttpStatus: {}", httpStatus);
		return ResponseEntity.status(httpStatus).build();
	}

	@PostMapping("/user")
	public ResponseEntity<Void> userAdd(@RequestBody UserDto userDto) {

		log.info("[userAdd] param - userDto: {}", userDto.toString());

		boolean result = userService.addUser(userDto);

		HttpStatus httpStatus = null;

		if (result) {
			httpStatus = HttpStatus.CREATED;
		} else {
			httpStatus = HttpStatus.BAD_REQUEST;
		}

		log.info("[userAdd] return - HttpStatus: {}", httpStatus);
		return ResponseEntity.status(httpStatus).build();
	}

	@PutMapping("/user/role")
	public ResponseEntity<Void> userRoleModify(@RequestBody Map<String, Object> paramMap) {

		String email = (String)paramMap.get("email");
		String role = (String)paramMap.get("role");
		log.info("[userRoleModify] param - email: {}, role: {}", email, role);

		boolean result = userService.modifyRole(email, role);

		log.info("[userRoleModify] return - result: {}", result);
		return result;
	}

}
