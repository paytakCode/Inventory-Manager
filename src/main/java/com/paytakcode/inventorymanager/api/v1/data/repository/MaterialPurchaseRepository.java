package com.paytakcode.inventorymanager.api.v1.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.paytakcode.inventorymanager.api.v1.data.entity.MaterialPurchase;

public interface MaterialPurchaseRepository extends JpaRepository<MaterialPurchase, Long> {

	@Query("SELECT SUM(mp.quantity) FROM MaterialPurchase mp WHERE mp.status = 'COMPLETED' OR mp.status = 'RECEIVED' AND mp.material.id = :materialId")
	Integer getTotalPurchaseQuantityById(Long materialId);

	@Query("SELECT SUM(mp.quantity) FROM MaterialPurchase mp WHERE mp.status = 'ORDERED' AND mp.material.id = :materialId")
	Integer getExpectedInboundQuantityById(Long materialId);
}