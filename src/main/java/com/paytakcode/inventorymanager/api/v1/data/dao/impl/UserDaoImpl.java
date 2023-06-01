package com.paytakcode.inventorymanager.api.v1.data.dao.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.paytakcode.inventorymanager.api.v1.data.dao.UserDao;
import com.paytakcode.inventorymanager.api.v1.data.entity.UserEntity;
import com.paytakcode.inventorymanager.api.v1.data.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * User DAO Implementation
 * @Author 김태산
 * @Version 0.2.0
 * @Since 2023-05-18 오후 3:45
 */

@Repository
@Slf4j
@RequiredArgsConstructor
public class UserDaoImpl implements UserDao {

	private final UserRepository userRepository;

	public UserEntity saveUser(UserEntity userEntity) {
		log.info("[saveUser] param - userEntity: {}", userEntity.toString());

		UserEntity savedUserEntity = userRepository.save(userEntity);

		log.info("[saveUser] return - savedUserEntity: {}", savedUserEntity);
		return savedUserEntity;
	}

	@Override
	public List<UserEntity> findUserList() {
		log.info("[findUserList] param - none");

		List<UserEntity> foundUserList = userRepository.findAll();

		log.info("[findUserList] return - foundUserList: {}", foundUserList);
		return foundUserList;
	}

	@Override
	public Optional<UserEntity> findUserById(Long userId) {
		log.info("[findUserById] param - userId: {}", userId);

		Optional<UserEntity> foundUser = userRepository.findById(userId);

		log.info("[findUserById] return - foundUser: {}", foundUser);
		return foundUser;
	}

	@Override
	public Optional<UserEntity> findUserByEmail(String email) {
		log.info("[findUserByEmail] param - email: {}", email);

		Optional<UserEntity> foundUserEntity = userRepository.findUserByEmail(email);

		log.info("[findUserByEmail] return - foundUserEntity: {}", foundUserEntity);
		return foundUserEntity;
	}

	@Override
	public UserEntity getUserReferenceByEmail(String email) {
		log.info("[getUserReferenceByEmail] param - email: {}", email);

		UserEntity gotUserEntity = userRepository.getReferenceByEmail(email);

		log.info("[getUserReferenceByEmail] return - gotUserEntity: {}", gotUserEntity);
		return gotUserEntity;
	}

	@Override
	public UserEntity getUserReferenceById(Long managerId) {
		log.info("[getUserReferenceById] param - managerId: {}", managerId);

		UserEntity gotUserEntity = userRepository.getReferenceById(managerId);

		log.info("[getUserReferenceById] return - gotUserEntity: {}", gotUserEntity);
		return gotUserEntity;
	}
}
