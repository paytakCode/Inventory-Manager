package com.paytakcode.inventorymanager.api.v1.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paytakcode.inventorymanager.api.v1.data.entity.Product;

/**
 * Product Repository
 * @Author 김태산
 * @Version 0.1.0
 * @Since 2023-05-25 오전 9:19
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
}
