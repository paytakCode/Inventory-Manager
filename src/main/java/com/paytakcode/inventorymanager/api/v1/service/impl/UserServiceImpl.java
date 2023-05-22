package com.paytakcode.inventorymanager.api.v1.service.impl;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Service;

import com.paytakcode.inventorymanager.api.v1.data.dao.UserDao;
import com.paytakcode.inventorymanager.api.v1.data.dto.LoginDto;
import com.paytakcode.inventorymanager.api.v1.data.dto.UserDto;
import com.paytakcode.inventorymanager.api.v1.data.emum.Role;
import com.paytakcode.inventorymanager.api.v1.data.entity.UserEntity;
import com.paytakcode.inventorymanager.api.v1.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * User Service 구현체
 * @Author 김태산
 * @Version 0.1.3
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

		// 인증 매니저를 사용하여 인증 처리, 인증 실패 -> AuthenticationException
		Authentication authentication = authenticationManager.authenticate(token);

		// 인증 성공한 경우, SecurityContext 인증 객체를 설정
		SecurityContextHolder.getContext().setAuthentication(authentication);

		HttpSession session = request.getSession(true);
		session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
			SecurityContextHolder.getContext());

		log.info("[login] return - login Email: {}", loginDto.getEmail());
		return loginDto.getEmail();
	}

	@Override
	public void modifyRole(String email, String role) {
		log.info("[modifyRole] param - email: {}, role: {}", email, role);

		if (isAdminUser()) {
			switch (role) {
				case "영업부":
					userDao.updateRole(email, Role.ROLE_SALES);
					break;
				case "자재부":
					userDao.updateRole(email, Role.ROLE_MATERIAL);
					break;
				case "생산부":
					userDao.updateRole(email, Role.ROLE_PRODUCTION);
					break;
				default:
					throw new IllegalArgumentException("[modifyRole] Unexpected Value: " + role);
			}
		} else {
			throw new AccessDeniedException("[modifyRole] Access Denied");
		}

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

	/**
	 * 접근하는 유저가 ROLE_ADMIN 권한을 가지고 있는지 확인하는 메서드
	 */
	public boolean isAdminUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			for (GrantedAuthority authority : authentication.getAuthorities()) {
				String grantedAuthority = authority.getAuthority();
				log.info("[isAdminUser] grantedAuthority: {}", grantedAuthority);
				if ("ROLE_ADMIN".equals(grantedAuthority)) {
					return true;
				}
			}
		}
		return false;
	}

}
