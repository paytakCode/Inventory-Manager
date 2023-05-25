package com.paytakcode.inventorymanager.api.v1.service;

import com.paytakcode.inventorymanager.api.v1.data.dto.BuyerDto;
import com.paytakcode.inventorymanager.api.v1.data.dto.SalesOrderDto;

/**
 * Sales Service
 * @Author 김태산
 * @Version 0.1.0
 * @Since 2023-05-26 오후 3:15
 */
public interface SalesService {
    BuyerDto addBuyer(BuyerDto buyerDto);

    SalesOrderDto addSalesOrder(SalesOrderDto salesOrderDto);

    void updateSalesOrder(Long salesOrderId, SalesOrderDto salesOrderDto);
}
