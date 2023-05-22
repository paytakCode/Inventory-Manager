package com.paytakcode.inventorymanager.api.v1.data.dao.impl;

import javax.transaction.Transactional;

import org.springframework.dao.DataAccessException;
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
 * @Version 0.1.1
 * @Since 2023-05-18 오후 3:45
 */

@Slf4j
@Repository
@Transactional
@RequiredArgsConstructor
public class UserDaoImpl implements UserDao {

	private final UserRepository userRepository;

	public boolean saveUser(UserEntity userEntity) {

		log.info("[saveUser] param - userEntity: {}", userEntity.toString());

		try {
			userRepository.save(userEntity);
			return true;
		} catch (DataAccessException e) {
			log.error("[saveUser] return - false, DataAccessException - Failed to save user: {}", e.getMessage());
			return false;
		}
	}

	public boolean updateRole(String email, Role role) {
		log.info("[updateRole] param - email: {}, role: {}", email, role);
		UserEntity userEntity = userRepository.findByEmail(email)
			.orElseThrow(() -> new UsernameNotFoundException("[updateRole] User not found with email: " + email));

		userEntity.setRole(role);
		try {
			userRepository.save(userEntity);
			log.info("[updateRole] return - true");
			return true;
		} catch (DataAccessException e) {
			log.error("[updateRole] return - false, DataAccessException - Failed to save user: {}", e.getMessage());
			return false;
		}
	}
}
