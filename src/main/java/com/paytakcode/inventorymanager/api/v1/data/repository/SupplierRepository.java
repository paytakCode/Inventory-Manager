package com.paytakcode.inventorymanager.api.v1.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paytakcode.inventorymanager.api.v1.data.entity.Supplier;

/**
 * Supplier Repository
 * @Author 김태산
 * @Version 0.1.0
 * @Since 2023-05-24 오후 3:59
 */

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}
