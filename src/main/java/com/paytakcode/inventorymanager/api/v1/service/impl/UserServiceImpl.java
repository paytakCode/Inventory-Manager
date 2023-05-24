package com.paytakcode.inventorymanager.api.v1.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.paytakcode.inventorymanager.api.v1.data.dao.UserDao;
import com.paytakcode.inventorymanager.api.v1.data.dto.LoginDto;
import com.paytakcode.inventorymanager.api.v1.data.dto.UserDto;
import com.paytakcode.inventorymanager.api.v1.data.emum.Role;
import com.paytakcode.inventorymanager.api.v1.data.entity.UserEntity;
import com.paytakcode.inventorymanager.api.v1.service.UserService;
import com.paytakcode.inventorymanager.api.v1.util.JwtUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * User Service 구현체
 * @Author 김태산
 * @Version 0.2.0
 * @Since 2023-05-18 오후 3:43
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final JwtUtil jwtUtil;
	private final UserDao userDao;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;

	@Override
	public String addUser(UserDto userDto) {
		log.info("[addUser] param - userDto: {}", userDto.toString());

		UserEntity savedUserEntity = userDao.saveUser(UserEntity.builder()
			.email(userDto.getEmail())
			.name(userDto.getName())
			.tel(userDto.getTel())
			.password(passwordEncoder.encode(userDto.getPassword()))
			.role(defaultRole(userDto.getName()))
			.build());

		log.info("[addUser] return - savedUserEmail: {}", savedUserEntity.getEmail());
		return savedUserEntity.getEmail();
	}

	@Override
	public String login(LoginDto loginDto, HttpServletRequest request) {
		log.info("[login] param - loginDto: {}, request: {}", loginDto.toString(), request);

		// 사용자 인증을 위해 Authentication 객체 생성
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginDto.getEmail(),
			loginDto.getPassword());

		// 인증 매니저를 사용하여 인증 처리(loadUserByUsername 호출), 인증 실패 -> AuthenticationException
		Authentication authentication = authenticationManager.authenticate(token);

		UserEntity foundUserEntity = userDao.findByEmail(loginDto.getEmail());

		String jwt = jwtUtil.createJwt(foundUserEntity);
		// 인증 성공한 경우, SecurityContext 인증 객체를 설정
		SecurityContextHolder.getContext().setAuthentication(authentication);

		log.info("[login] return - jwt: {}", jwt);
		return jwt;
	}

	@Override
	public void modifyRole(Long userId, Role role) {
		log.info("[modifyRole] param - userId: {}, role: {}", userId, role);

		userDao.updateRole(userId, role);
	}

	/**
	 * Admin Role을 자동으로 부여하기 위한 임시 메서드
	 */
	private Role defaultRole(String name) {
		if (name.equals("admin")) {
			return Role.ROLE_ADMIN;
		} else {
			return Role.ROLE_WAIT;
		}
	}

}
