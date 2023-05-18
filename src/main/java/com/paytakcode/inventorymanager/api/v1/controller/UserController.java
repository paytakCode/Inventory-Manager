package com.paytakcode.inventorymanager.api.v1.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.paytakcode.inventorymanager.api.v1.data.dto.RegisterDto;
import com.paytakcode.inventorymanager.api.v1.data.dto.UserDto;
import com.paytakcode.inventorymanager.api.v1.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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
	private final AuthenticationManager authenticationManager;

	@PostMapping("/login")
	public String login(String email, String password,
		HttpServletRequest request) {
		log.info("[login] email: {}, password: {}", email, password);
		try {
			// 사용자 인증을 위해 Authentication 객체 생성
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(email, password);

			// 인증 매니저를 사용하여 인증 처리
			Authentication authentication = authenticationManager.authenticate(token);

			// 인증 성공한 경우, SecurityContext에 인증 객체를 설정
			SecurityContextHolder.getContext().setAuthentication(authentication);

			// 세션 설정
			HttpSession session = request.getSession(true);
			session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
				SecurityContextHolder.getContext());

			return "redirect:/main"; // 로그인 성공 후 리다이렉트할 URL
		} catch (AuthenticationException e) {
			// 인증 실패한 경우 처리
			log.info("인증 실패");
			return "redirect:/login?error"; // 로그인 실패 시 리다이렉트할 URL
		}
	}

	@PostMapping("/signup")
	public String signUp(@RequestBody RegisterDto registerDto) {
		log.info("[signUp] Register Dto {}", registerDto.toString());
		UserDto userDto = userService.addUser(registerDto);
		log.info("[signUp] saved Dto {}", userDto.toString());
		return "redirect:login";
	}

}
