package com.paytakcode.inventorymanager.api.v1.controller;

import com.paytakcode.inventorymanager.api.v1.data.dto.UserDto;
import com.paytakcode.inventorymanager.api.v1.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * User Controller
 * @Author 김태산
 * @Version 0.1.0
 * @Since 2023-05-18 오후 3:40
 */

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserController {

	private final UserService userService;

	@PostMapping("/login")
	public String login(@RequestParam String email, @RequestParam String password,
						HttpServletRequest request) {
		log.info("[login] param - email: {}, password: {}, request: {}", email, password, request);

		boolean result = userService.login(email, password, request);

		if(result){
			return "redirect:main";
		} else {
			return "redirect:login?error";
		}

	}

	@PostMapping("/user")
	public String userAdd(@RequestBody UserDto userDto) {
		log.info("[userAdd] param - userDto: {}", userDto.toString());

		UserDto AddeduserDto = userService.addUser(userDto); // 회원 가입 결과에 따른 로직 미구현

		return "redirect:login";
	}

}
