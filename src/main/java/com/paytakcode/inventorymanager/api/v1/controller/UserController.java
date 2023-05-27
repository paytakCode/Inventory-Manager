package com.paytakcode.inventorymanager.api.v1.controller;

import javax.validation.Valid;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paytakcode.inventorymanager.api.v1.data.dto.LoginDto;
import com.paytakcode.inventorymanager.api.v1.data.dto.RoleDto;
import com.paytakcode.inventorymanager.api.v1.data.dto.UserDto;
import com.paytakcode.inventorymanager.api.v1.data.dto.UserInfoDto;
import com.paytakcode.inventorymanager.api.v1.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * User Controller
 * @Author 김태산
 * @Version 0.2.0
 * @Since 2023-05-18 오후 3:40
 */

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserController {

	private final UserService userService;

	@PostMapping("/users")
	public ResponseEntity<UserInfoDto> userAdd(@RequestBody @Valid UserDto userDto) {
		log.info("[userAdd] param - userDto: {}", userDto.toString());

		UserInfoDto addedUserInfoDto = userService.addUser(userDto);

		log.info("[userAdd] return - HttpStatus.CREATED(201), addedUserInfoDto: {}", addedUserInfoDto);
		return ResponseEntity
			.status(HttpStatus.CREATED)
			.body(addedUserInfoDto);
	}

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody @Valid LoginDto loginDto) {
		log.info("[login] param - loginDTO: {}", loginDto);

		String jwt = userService.login(loginDto);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + jwt);
		log.info("[login] return - HttpStatus.OK(200), jwt: [PROTECTED]");
		log.debug("[login] jwt: {}", jwt);
		return ResponseEntity
			.status(HttpStatus.OK)
			.headers(headers)
			.body("Login successful");
	}

	@PutMapping("/admin/users/{userId}/role")
	public ResponseEntity<Void> userRoleUpdate(@PathVariable Long userId, @RequestBody @Valid RoleDto roleDto) {
		log.info("[userRoleUpdate] param - userId: {}, role: {}", userId, roleDto.getRole());

		userService.updateUserRole(userId, roleDto.getRole());

		log.info("[userRoleUpdate] return - HttpStatus.NO_CONTENT(204)");
		return ResponseEntity
			.status(HttpStatus.NO_CONTENT)
			.build();
	}

}
