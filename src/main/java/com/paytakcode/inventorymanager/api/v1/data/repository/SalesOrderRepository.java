package com.paytakcode.inventorymanager.api.v1.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.paytakcode.inventorymanager.api.v1.data.entity.SalesOrder;

/**
 * Sales Repository
 * @Author 김태산
 * @Version 0.1.0
 * @Since 2023-05-26 오후 3:18
 */
public interface SalesOrderRepository extends JpaRepository<SalesOrder, Long> {

	@Query("SELECT SUM(o.quantity) "
		+ "FROM SalesOrder o "
		+ "WHERE "
		+ "o.status = 'READY_SHIPMENT'"
		+ "OR o.status = 'SHIPPED'"
		+ "OR o.status = 'DELIVERED'"
		+ "OR o.status = 'COMPLETED'"
		+ "AND o.product.id = :productId")
	Integer findTotalSalesOrderQuantityByProductId(@Param("productId") Long productId);
}
