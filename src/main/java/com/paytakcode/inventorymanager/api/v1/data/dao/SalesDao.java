package com.paytakcode.inventorymanager.api.v1.data.dao;

import java.util.Optional;

import com.paytakcode.inventorymanager.api.v1.data.entity.Buyer;
import com.paytakcode.inventorymanager.api.v1.data.entity.SalesOrder;

/**
 * Sales DAO
 * @Author 김태산
 * @Version 0.1.0
 * @Since 2023-05-26 오후 3:16
 */
public interface SalesDao {
    Buyer saveBuyer(Buyer buyer);

    Integer findTotalSalesOrderQuantityByProductId(Long productId);

    Buyer getBuyerReferenceById(Long buyerId);

    SalesOrder saveSalesOrder(SalesOrder salesOrder);

    Optional<SalesOrder> findSalesOrderById(Long salesOrderId);
}
