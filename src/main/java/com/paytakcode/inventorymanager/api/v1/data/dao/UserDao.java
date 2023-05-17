package com.paytakcode.inventorymanager.api.v1.data.dao;

import com.paytakcode.inventorymanager.api.v1.data.entity.User;

/**
 * User DAO
 * @Author 김태산
 * @Version 0.1.0
 * @Since 2023-05-18 오전 12:25
 */
public interface UserDao {
	User saveUser(User user);
}
