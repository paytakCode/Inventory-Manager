package com.paytakcode.inventorymanager.api.v1.data.dao.impl;

import javax.transaction.Transactional;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.paytakcode.inventorymanager.api.v1.data.dao.UserDao;
import com.paytakcode.inventorymanager.api.v1.data.emum.Role;
import com.paytakcode.inventorymanager.api.v1.data.entity.UserEntity;
import com.paytakcode.inventorymanager.api.v1.data.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * User DAO Implementation
 * @Author 김태산
 * @Version 0.1.3
 * @Since 2023-05-18 오후 3:45
 */

@Slf4j
@Repository
@Transactional
@RequiredArgsConstructor
public class UserDaoImpl implements UserDao {

	private final UserRepository userRepository;

	public UserEntity saveUser(UserEntity userEntity) {
		log.info("[saveUser] param - userEntity: {}", userEntity.toString());

		UserEntity savedUserEntity = userRepository.save(userEntity);

		log.info("[saveUser] return - savedUserEntity: {}", savedUserEntity);
		return savedUserEntity;
	}

	public void updateRole(Long userId, Role role) {
		log.info("[updateRole] param - userId: {}, role: {}", userId, role);

		UserEntity userEntity = userRepository.findById(userId)
			.orElseThrow(() -> new UsernameNotFoundException("[updateRole] User not found with userId: " + userId));

		userEntity.setRole(role);

		userRepository.save(userEntity);
	}

	@Override
	public UserEntity findByEmail(String email) {
		log.info("[findByEmail] param - email: {}", email);

		UserEntity foundUserEntity = userRepository.findByEmail(email)
			.orElseThrow(() -> new UsernameNotFoundException("[findByEmail] User not found with email: " + email));

		log.info("[findByEmail] return - foundUserEntity: {}", foundUserEntity);
		return foundUserEntity;
	}
}
