package com.paytakcode.inventorymanager.api.v1.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paytakcode.inventorymanager.api.v1.data.entity.MaterialPurchase;

public interface MaterialPurchaseRepository extends JpaRepository<MaterialPurchase, Long> {
}