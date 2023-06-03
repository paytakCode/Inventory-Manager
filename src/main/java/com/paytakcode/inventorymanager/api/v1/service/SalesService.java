package com.paytakcode.inventorymanager.api.v1.service;

import java.util.List;

import com.paytakcode.inventorymanager.api.v1.data.dto.BuyerDto;
import com.paytakcode.inventorymanager.api.v1.data.dto.SalesOrderDto;

/**
 * Sales Service
 * @Author 김태산
 * @Version 0.4.0
 * @Since 2023-05-26 오후 3:15
 */
public interface SalesService {
	BuyerDto addBuyer(BuyerDto buyerDto);

	List<BuyerDto> getBuyerList();

	BuyerDto getBuyerById(Long buyerId);

	void updateBuyer(Long buyerId, BuyerDto buyerDto);

	void deleteBuyerById(Long buyerId);

	SalesOrderDto addSalesOrder(SalesOrderDto salesOrderDto);

	List<SalesOrderDto> getSalesOrderList();

	SalesOrderDto getSalesOrderById(Long salesOrderId);

	void updateSalesOrder(Long salesOrderId, SalesOrderDto salesOrderDto);

	void deleteSalesOrderById(Long salesOrderId);
}
