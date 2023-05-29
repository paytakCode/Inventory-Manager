package com.paytakcode.inventorymanager.api.v1.service.impl;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paytakcode.inventorymanager.api.v1.data.dao.ProductDao;
import com.paytakcode.inventorymanager.api.v1.data.dao.SalesDao;
import com.paytakcode.inventorymanager.api.v1.data.dto.BuyerDto;
import com.paytakcode.inventorymanager.api.v1.data.dto.SalesOrderDto;
import com.paytakcode.inventorymanager.api.v1.data.emum.OrderStatus;
import com.paytakcode.inventorymanager.api.v1.data.entity.Buyer;
import com.paytakcode.inventorymanager.api.v1.data.entity.Product;
import com.paytakcode.inventorymanager.api.v1.data.entity.SalesOrder;
import com.paytakcode.inventorymanager.api.v1.service.SalesService;
import com.paytakcode.inventorymanager.api.v1.util.DtoToEntityMapper;
import com.paytakcode.inventorymanager.api.v1.util.EntityToDtoMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Sales Service Implementation
 * @Author 김태산
 * @Version 0.2.0
 * @Since 2023-05-26 오후 3:15
 */

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class SalesServiceImpl implements SalesService {

    private final DtoToEntityMapper dtoToEntityMapper;
    private final SalesDao salesDao;
    private final ProductDao productDao;

    @Override
    public BuyerDto addBuyer(BuyerDto buyerDto) {
        log.info("[addBuyer] param - buyerDto: {}", buyerDto);

		Buyer buyer = dtoToEntityMapper.convertBuyerDtoToEntity(buyerDto);

		Buyer savedBuyer = salesDao.saveBuyer(buyer);

		BuyerDto savedBuyerDto = EntityToDtoMapper.convertBuyerToDto(savedBuyer);

		log.info("[addBuyer] param - savedBuyerDto: {}", savedBuyerDto);
		return savedBuyerDto;
	}

	@Override
	public BuyerDto getBuyerById(Long buyerId) {
		log.info("[getBuyerById] param - buyerId: {}", buyerId);

		Buyer foundBuyer = salesDao.findBuyerById(buyerId)
			.orElseThrow();

		BuyerDto foundBuyerDto = EntityToDtoMapper.convertBuyerToDto(foundBuyer);

		log.info("[getBuyerById] return - foundBuyerDto: {}", foundBuyerDto);
		return foundBuyerDto;
	}

	@Override
	public void updateBuyer(Long buyerId, BuyerDto buyerDto) {
		log.info("[updateBuyer] param - buyerId: {}, buyerDto: {}", buyerId, buyerDto);

		Buyer buyer = salesDao.findBuyerById(buyerId)
			.orElseThrow();

		buyer.setCompanyName(buyerDto.getCompanyName());
		buyer.setManagerName(buyerDto.getManagerName());
		buyer.setTel(buyerDto.getTel());
		buyer.setLoc(buyerDto.getLoc());

		Buyer updatedBuyer = salesDao.saveBuyer(buyer);

		BuyerDto updatedBuyerDto = EntityToDtoMapper.convertBuyerToDto(updatedBuyer);

		log.info("[updateBuyer] result - updatedBuyerDto: {}", updatedBuyerDto);
	}

	@Override
	public void deleteBuyerById(Long buyerId) {
		log.info("[deleteBuyerById] param - buyerId: {}", buyerId);

		salesDao.deleteBuyerById(buyerId);
	}

	@Override
	public SalesOrderDto addSalesOrder(SalesOrderDto salesOrderDto) {
		log.info("[addSalesOrder] param - orderDto: {}", salesOrderDto);

		SalesOrder salesOrder = dtoToEntityMapper.convertSalesOrderDtoToEntity(salesOrderDto);

		SalesOrder savedSalesOrder = salesDao.saveSalesOrder(salesOrder);

		SalesOrderDto savedSalesOrderDto = EntityToDtoMapper.convertSalesOrderToDto(savedSalesOrder);

        log.info("[addSalesOrder] param - savedSalesOrderDto: {}", savedSalesOrderDto);
        return savedSalesOrderDto;
    }

    @Override
    public void updateSalesOrder(Long salesOrderId, SalesOrderDto salesOrderDto) {
        log.info("[updateSalesOrder] param - salesOrderId: {}, salesOrderDto: {}", salesOrderId, salesOrderDto);

        SalesOrder salesOrder = salesDao.findSalesOrderById(salesOrderId)
            .orElseThrow();

        Product product = productDao.getProductReferenceById(salesOrderDto.getProductId());
        Buyer buyer = salesDao.getBuyerReferenceById(salesOrderDto.getBuyerId());

        if(salesOrderDto.getStatus() == OrderStatus.COMPLETED
            && salesOrder.getStatus() != OrderStatus.COMPLETED){
            salesOrder.setCompletionDate(LocalDateTime.now());
        } else {
            salesOrder.setCompletionDate(null);
        }

        salesOrder.setProduct(product);
        salesOrder.setQuantity(salesOrderDto.getQuantity());
        salesOrder.setBuyer(buyer);
        salesOrder.setDueDate(salesOrderDto.getDueDate());
        salesOrder.setStatus(salesOrderDto.getStatus());

        SalesOrder updatedSalesOrder = salesDao.saveSalesOrder(salesOrder);

        SalesOrderDto updatedSalesOrderDto = EntityToDtoMapper.convertSalesOrderToDto(updatedSalesOrder);

        log.info("[updateSalesOrder] result - updatedSalesOrderDto: {}", updatedSalesOrderDto);
    }
}
