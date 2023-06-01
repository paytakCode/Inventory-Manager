package com.paytakcode.inventorymanager.api.v1.service.impl;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paytakcode.inventorymanager.api.v1.data.dao.UserDao;
import com.paytakcode.inventorymanager.api.v1.data.dto.LoginDto;
import com.paytakcode.inventorymanager.api.v1.data.dto.UserDto;
import com.paytakcode.inventorymanager.api.v1.data.dto.UserInfoDto;
import com.paytakcode.inventorymanager.api.v1.data.emum.Role;
import com.paytakcode.inventorymanager.api.v1.data.entity.UserEntity;
import com.paytakcode.inventorymanager.api.v1.service.UserService;
import com.paytakcode.inventorymanager.api.v1.util.DtoToEntityMapper;
import com.paytakcode.inventorymanager.api.v1.util.EntityToDtoMapper;
import com.paytakcode.inventorymanager.api.v1.util.JwtUtil;
import com.paytakcode.inventorymanager.api.v1.util.UserUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * User Service Implementation
 * @Author 김태산
 * @Version 0.5.0
 * @Since 2023-05-18 오후 3:43
 */
@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final DtoToEntityMapper dtoToEntityMapper;
	private final JwtUtil jwtUtil;
	private final UserDao userDao;
	private final AuthenticationManager authenticationManager;
	private final PasswordEncoder passwordEncoder;

	@Override
	public UserInfoDto addUser(UserDto userDto) {
		log.info("[addUser] param - userDto: {}", userDto.toString());

		UserEntity user = dtoToEntityMapper.convertUserDtoToEntity(userDto);

		UserEntity savedUser = userDao.saveUser(user);

		UserInfoDto savedUserInfoDto = EntityToDtoMapper.convertUserToUserInfoDto(savedUser);

		log.info("[addUser] return - savedUserInfoDto: {}", savedUserInfoDto);
		return savedUserInfoDto;
	}

	@Override
	public List<UserInfoDto> getUserList() {
		log.info("[getUserList] param - none");

		List<UserEntity> foundUserList = userDao.findUserList();

		List<UserInfoDto> foundUserDtoList = new ArrayList<>();

		for (UserEntity user : foundUserList) {
			foundUserDtoList.add(EntityToDtoMapper.convertUserToUserInfoDto(user));
		}

		log.info("[getUserList] return - foundUserDtoList: {}", foundUserDtoList);
		return foundUserDtoList;
	}

	@Override
	public UserInfoDto getUserInfoById(Long userId) {
		log.info("[userInfoById] param - userId: {}", userId);

		UserEntity foundUser = userDao.findUserById(userId)
			.orElseThrow();

		UserInfoDto foundUserInfoDto = EntityToDtoMapper.convertUserToUserInfoDto(
			foundUser);

		log.info("[userInfoById] return - foundUserDto: {}", foundUserInfoDto);
		return foundUserInfoDto;
	}

	@Override
	public void updateUser(Long userId, UserDto userDto) {
		log.info("[updateUser] param - userId: {}, userDto: {}", userId, userDto);

		UserEntity user = userDao.findUserById(userId)
			.orElseThrow();

		if (!isOwner(user)) {
			throw new AccessDeniedException("cannot update other User");
		}

		user.setEmail(userDto.getEmail());
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		user.setName(userDto.getName());
		user.setTel(userDto.getTel());

		UserEntity updatedUser = userDao.saveUser(user);

		UserDto updatedUserDto = EntityToDtoMapper.convertUserToUserDto(updatedUser);

		log.info("[updateUser] result - updatedUserDto: {}", updatedUserDto);
	}

	@Override
	public String login(LoginDto loginDto) {
		log.info("[login] param - loginDto: {}", loginDto);

		// 사용자 인증을 위해 Authentication 객체 생성
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
			loginDto.getEmail(),
			loginDto.getPassword());

		// 인증 매니저를 사용하여 인증 처리(loadUserByUsername 호출), 인증 실패 -> AuthenticationException
		Authentication authentication = authenticationManager.authenticate(authenticationToken);

		UserEntity user = userDao.findUserByEmail(loginDto.getEmail())
			.orElseThrow();

		String jwt = jwtUtil.createJwt(user);

		// 인증 성공한 경우, SecurityContext 인증 객체를 설정
		SecurityContextHolder.getContext().setAuthentication(authentication);

		log.info("[login] return - jwt: [PROTECTED]");
		log.debug("[login] jwt: {}", jwt);
		return jwt;
	}

	@Override
	public void logout(HttpServletRequest request) {
		log.info("[logout] param - request: [PROTECTED]");

		final String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
		final String jwt = authorization.split(" ")[1];

		jwtUtil.invalidateToken(jwt);

		log.info("[logout] result - JWT invalidated");
	}

	@Override
	public void updateUserRole(Long userId, Role role) {
		log.info("[updateUserRole] param - userId: {}, role: {}", userId, role);

		UserEntity user = userDao.findUserById(userId)
			.orElseThrow();

		user.setRole(role);

		UserEntity updatedUser = userDao.saveUser(user);

		UserInfoDto updatedUserInfoDto = EntityToDtoMapper.convertUserToUserInfoDto(updatedUser);

		log.info("[updateUserRole] result - updatedUserInfoDto: {}", updatedUserInfoDto);
	}

	private boolean isOwner(UserEntity user) {
		return user.getEmail().equals(UserUtil.getCurrentUserEmail());
	}
}
