package com.paytakcode.inventorymanager.api.v1.data.dao;

import java.util.List;
import java.util.Optional;

import com.paytakcode.inventorymanager.api.v1.data.entity.Buyer;
import com.paytakcode.inventorymanager.api.v1.data.entity.SalesOrder;

/**
 * Sales DAO
 * @Author 김태산
 * @Version 0.4.0
 * @Since 2023-05-26 오후 3:16
 */
public interface SalesDao {
    Buyer saveBuyer(Buyer buyer);

    List<Buyer> findBuyerList();

    Optional<Buyer> findBuyerById(Long buyerId);

    void deleteBuyerById(Long buyerId);

    Integer findTotalSalesOrderQuantityByProductId(Long productId);

    Buyer getBuyerReferenceById(Long buyerId);

    SalesOrder saveSalesOrder(SalesOrder salesOrder);

    List<SalesOrder> findSalesOrderList();

    Optional<SalesOrder> findSalesOrderById(Long salesOrderId);

    void deleteSalesOrderById(Long salesOrderId);
}
