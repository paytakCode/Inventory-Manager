package com.paytakcode.inventorymanager.api.v1.data.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paytakcode.inventorymanager.api.v1.data.entity.UserEntity;

/**
 * UserRepository
 * @Author 김태산
 * @Version 0.1.0
 * @Since 2023-05-18 오후 2:54
 */
public interface UserRepository extends JpaRepository<UserEntity, Long> {
	Optional<UserEntity> findByEmail(String email);
}
