package com.paytakcode.inventorymanager.api.v1.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paytakcode.inventorymanager.api.v1.data.entity.User;

/**
 * User 인증 관련 Repository
 * @Author 김태산
 * @Version 0.1.0
 * @Since 2023-05-18 오전 12:16
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
