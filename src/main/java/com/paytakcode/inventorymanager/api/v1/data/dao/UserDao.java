package com.paytakcode.inventorymanager.api.v1.data.dao;

import com.paytakcode.inventorymanager.api.v1.data.emum.Role;
import com.paytakcode.inventorymanager.api.v1.data.entity.UserEntity;

/**
 * User DAO
 * @Author 김태산
 * @Version 0.1.2
 * @Since 2023-05-18 오후 3:45
 */
public interface UserDao {
	UserEntity saveUser(UserEntity userEntity);

	UserEntity updateRole(String email, Role role);
}
