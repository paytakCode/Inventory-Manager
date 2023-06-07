package com.paytakcode.inventorymanager.api.v1.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.paytakcode.inventorymanager.api.v1.data.emum.PurchaseStatus;
import com.paytakcode.inventorymanager.api.v1.data.entity.MaterialPurchase;

public interface MaterialPurchaseRepository extends JpaRepository<MaterialPurchase, Long> {

	@Query("SELECT SUM(mp.quantity) "
		+ "FROM MaterialPurchase mp "
		+ "WHERE mp.status = :purchaseStatus "
		+ "AND mp.material.id = :materialId")
	Integer findTotalMaterialQuantityByMaterialIdAndStatus(@Param("materialId") Long materialId,
		@Param("purchaseStatus") PurchaseStatus purchaseStatus);
}