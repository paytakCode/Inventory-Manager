package com.paytakcode.inventorymanager.api.v1.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Service;

import com.paytakcode.inventorymanager.api.v1.data.dao.UserDao;
import com.paytakcode.inventorymanager.api.v1.data.dto.UserDto;
import com.paytakcode.inventorymanager.api.v1.data.emum.Role;
import com.paytakcode.inventorymanager.api.v1.data.entity.UserEntity;
import com.paytakcode.inventorymanager.api.v1.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * User Service 구현체
 * @Author 김태산
 * @Version 0.1.1
 * @Since 2023-05-18 오후 3:43
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserDao userDao;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;

	@Override
	public UserDto addUser(UserDto userDto) {
		log.info("[addUser] param - userDto: {}", userDto.toString());
		UserEntity user = userDao.saveUser(UserEntity.builder()
			.email(userDto.getEmail())
			.name(userDto.getName())
			.tel(userDto.getTel())
			.password(passwordEncoder.encode(userDto.getPassword()))
			.role(Role.ROLE_WAIT)
			.build());

		return UserDto.builder()
			.email(user.getEmail())
			.name(user.getName())
			.tel(user.getTel())
			.password(user.getPassword())
			.role(user.getRole())
			.build();
	}

	@Override
	public boolean login(String email, String password, HttpServletRequest request) {
		log.info("[login] param - email: {}, password: {}, request: {}", email, password, request);
		try {
			// 사용자 인증을 위해 Authentication 객체 생성
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(email, password);

			// 인증 매니저를 사용하여 인증 처리
			Authentication authentication = authenticationManager.authenticate(token);

			// 인증 성공한 경우, SecurityContext에 인증 객체를 설정
			SecurityContextHolder.getContext().setAuthentication(authentication);

			HttpSession session = request.getSession(true);
			session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
					SecurityContextHolder.getContext());

			log.info("[login] result - login success");
			return true;
		} catch (AuthenticationException e) {
			log.error("[login] result - login failed");
			return false;
		}
	}

}
