package com.paytakcode.inventorymanager.api.v1.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.paytakcode.inventorymanager.api.v1.data.entity.Production;

public interface ProductionRepository extends JpaRepository<Production, Long> {

	@Query("SELECT SUM(p.quantity) FROM Production p WHERE p.status = 'COMPLETED' AND p.product.id = :productId")
	Integer findTotalProductionQuantityByProductId(@Param("productId") Long productId);
}