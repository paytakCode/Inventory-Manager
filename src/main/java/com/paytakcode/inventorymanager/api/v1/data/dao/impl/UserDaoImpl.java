package com.paytakcode.inventorymanager.api.v1.data.dao.impl;

import org.springframework.stereotype.Repository;

import com.paytakcode.inventorymanager.api.v1.data.dao.UserDao;
import com.paytakcode.inventorymanager.api.v1.data.entity.User;
import com.paytakcode.inventorymanager.api.v1.data.repository.UserRepository;

import lombok.RequiredArgsConstructor;

/**
 * UserDaoImpl
 * @Author 김태산
 * @Version 0.1.0
 * @Since 2023-05-18 오전 12:25
 */
@Repository
@RequiredArgsConstructor
public class UserDaoImpl implements UserDao {

	private final UserRepository userRepository;

	@Override
	public User saveUser(User user){
		return userRepository.save(user);
	}
}
