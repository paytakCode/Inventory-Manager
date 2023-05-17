package com.paytakcode.inventorymanager.data.repository;

import com.paytakcode.inventorymanager.data.entity.MySqlTest;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * MySql 연동 테스트를 위한 JPA Respository입니다.
 * @Author 김태산
 * @Version 0.0.1
 * @Since 2023-05-17
 */
public interface MySqlTestRepository extends JpaRepository<MySqlTest, Long> {
}
