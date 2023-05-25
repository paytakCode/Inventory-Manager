package com.paytakcode.inventorymanager.api.v1.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paytakcode.inventorymanager.api.v1.data.entity.Buyer;

/**
 * Buyer Repository
 * @Author 김태산
 * @Version 0.1.0
 * @Since 2023-05-26 오후 3:19
 */
public interface BuyerRepository extends JpaRepository<Buyer, Long> {
}
