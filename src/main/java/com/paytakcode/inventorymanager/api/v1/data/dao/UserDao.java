package com.paytakcode.inventorymanager.api.v1.data.dao;

import java.util.Optional;

import com.paytakcode.inventorymanager.api.v1.data.entity.UserEntity;

/**
 * User DAO
 * @Author 김태산
 * @Version 0.1.4
 * @Since 2023-05-18 오후 3:45
 */
public interface UserDao {
	UserEntity saveUser(UserEntity userEntity);

	Optional<UserEntity> findUserById(Long userId);

	Optional<UserEntity> findUserByEmail(String email);

	UserEntity getUserReferenceByEmail(String email);

	UserEntity getUserReferenceById(Long managerId);
}
