package com.paytakcode.inventorymanager.api.v1.data.dao.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import com.paytakcode.inventorymanager.api.v1.data.dao.UserDao;
import com.paytakcode.inventorymanager.api.v1.data.entity.UserEntity;
import com.paytakcode.inventorymanager.api.v1.data.repository.UserRepository;

import lombok.RequiredArgsConstructor;

/**
 * User DAO Implementation
 * @Author 김태산
 * @Version 0.1.0
 * @Since 2023-05-18 오후 3:45
 */

@Slf4j
@Repository
@RequiredArgsConstructor
public class UserDaoImpl implements UserDao {

	private final UserRepository userRepository;

	public UserEntity saveUser(UserEntity userEntity) {
		log.info("[saveUser] param - userEntity: {}", userEntity.toString());
		UserEntity savedUserEntity = userRepository.save(userEntity);
		log.info("[saveUser] result - savedUserEntity: {}", savedUserEntity);
		return savedUserEntity;
	}
}
