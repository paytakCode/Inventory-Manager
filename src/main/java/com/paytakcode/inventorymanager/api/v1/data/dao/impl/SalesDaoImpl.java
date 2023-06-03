package com.paytakcode.inventorymanager.api.v1.data.dao.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.paytakcode.inventorymanager.api.v1.data.dao.SalesDao;
import com.paytakcode.inventorymanager.api.v1.data.entity.Buyer;
import com.paytakcode.inventorymanager.api.v1.data.entity.SalesOrder;
import com.paytakcode.inventorymanager.api.v1.data.repository.BuyerRepository;
import com.paytakcode.inventorymanager.api.v1.data.repository.SalesOrderRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @Author 김태산
 * @Version 0.4.0
 * @Since 2023-05-26 오후 3:17
 */

@Repository
@Slf4j
@RequiredArgsConstructor
public class SalesDaoImpl implements SalesDao {

    private final BuyerRepository buyerRepository;
    private final SalesOrderRepository salesOrderRepository;

	@Override
	public Buyer saveBuyer(Buyer buyer) {
		log.info("[saveBuyer] param - buyer: {}", buyer);

		Buyer savedBuyer = buyerRepository.save(buyer);

		log.info("[saveBuyer] param - savedBuyer: {}", savedBuyer);
		return savedBuyer;
	}

	@Override
	public List<Buyer> findBuyerList() {
		log.info("[findBuyerList] param - none");

		List<Buyer> foundBuyerList = buyerRepository.findAll();

		log.info("[findBuyerList] return - foundBuyerList: {}", foundBuyerList);
		return foundBuyerList;
	}

	@Override
	public Optional<Buyer> findBuyerById(Long buyerId) {
		log.info("[findBuyerById] param - buyerId: {}", buyerId);

		Optional<Buyer> foundBuyer = buyerRepository.findById(buyerId);

		log.info("[findBuyerById] return - foundBuyer: {}", foundBuyer);
		return foundBuyer;
	}

	@Override
	public void deleteBuyerById(Long buyerId) {
		log.info("[deleteBuyerById] param - buyerId: {}", buyerId);

		buyerRepository.deleteById(buyerId);

		log.info("[deleteBuyerById] result - buyer Deleted: {}", buyerId);
	}

	@Override
	public Integer findTotalSalesOrderQuantityByProductId(Long productId) {
		log.info("[findTotalSalesOrderQuantityByBuyerId] param - productId: {}", productId);

		Integer totalSalesOrderQuantity = salesOrderRepository.findTotalSalesOrderQuantityByProductId(productId);

		log.info("[findTotalSalesOrderQuantityByProductId] param - totalSalesOrderQuantity: {}",
			totalSalesOrderQuantity);
		return totalSalesOrderQuantity;
	}

    @Override
    public Buyer getBuyerReferenceById(Long buyerId) {
        log.info("[getBuyerReferenceById] param - buyerId: {}", buyerId);

        Buyer gotBuyerReference = buyerRepository.getReferenceById(buyerId);

        log.info("[getBuyerReferenceById] return - gotBuyerReference: {}", gotBuyerReference);
        return gotBuyerReference;
    }

	@Override
	public SalesOrder saveSalesOrder(SalesOrder salesOrder) {
		log.info("[saveSalesOrder] param - order: {}", salesOrder);

		SalesOrder savedSalesOrder = salesOrderRepository.save(salesOrder);

		log.info("[saveSalesOrder] param - savedSalesOrder: {}", savedSalesOrder);
		return savedSalesOrder;
	}

	@Override
	public List<SalesOrder> findSalesOrderList() {
		log.info("[findSalesOrderList] param - none");

		List<SalesOrder> foundSalesOrderList = salesOrderRepository.findAll();

		log.info("[findSalesOrderList] return - foundSalesOrderList: {}", foundSalesOrderList);
		return foundSalesOrderList;
	}

	@Override
	public Optional<SalesOrder> findSalesOrderById(Long salesOrderId) {
		log.info("[findSalesOrderById] param - salesOrderId: {}", salesOrderId);

		Optional<SalesOrder> foundSalesOrder = salesOrderRepository.findById(salesOrderId);

		log.info("[findSalesOrderById] return - foundSalesOrder: {}", foundSalesOrder);
		return foundSalesOrder;
	}

	@Override
	public void deleteSalesOrderById(Long salesOrderId) {
		log.info("[deleteSalesOrderById] param - salesOrderId: {}", salesOrderId);

		salesOrderRepository.deleteById(salesOrderId);

		log.info("[deleteSalesOrderById] result - salesOrder Deleted: {}", salesOrderId);
	}
}
