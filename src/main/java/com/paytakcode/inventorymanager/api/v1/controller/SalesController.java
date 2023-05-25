package com.paytakcode.inventorymanager.api.v1.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paytakcode.inventorymanager.api.v1.data.dto.BuyerDto;
import com.paytakcode.inventorymanager.api.v1.data.dto.SalesOrderDto;
import com.paytakcode.inventorymanager.api.v1.service.SalesService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Sales Controller
 * @Author 김태산
 * @Version 0.1.0
 * @Since 2023-05-26 오후 3:12
 */

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class SalesController {

    private final SalesService salesService;

    @PostMapping("/sales/buyers")
    public ResponseEntity<String> buyerAdd(@RequestBody @Valid BuyerDto buyerDto) {
        log.info("[buyerAdd] param - buyerDto: {}", buyerDto);

        BuyerDto addedBuyerDto = salesService.addBuyer(buyerDto);

        log.info("[buyerAdd] return - HttpStatus.CREATED(201), addedBuyerDto: {}", addedBuyerDto);
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(addedBuyerDto.toString());
    }

    @PostMapping("/sales/sales-orders")
    public ResponseEntity<String> salesOrderAdd(@RequestBody @Valid SalesOrderDto salesOrderDto) {
        log.info("[salesOrderAdd] param - orderDto: {}", salesOrderDto);

        SalesOrderDto addedSalesOrderDto = salesService.addSalesOrder(salesOrderDto);

        log.info("[salesOrderAdd] return - HttpStatus.CREATED(201), addedOrderDto: {}", addedSalesOrderDto);
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(addedSalesOrderDto.toString());
    }

    @PutMapping("/sales/sales-orders/{salesOrderId}")
    public ResponseEntity<String> salesOrderUpdate(@PathVariable Long salesOrderId, @RequestBody @Valid SalesOrderDto salesOrderDto) {
        log.info("[salesOrderUpdate] param - salesOrderId: {}, salesOrderDto: {}", salesOrderId, salesOrderDto);

        salesService.updateSalesOrder(salesOrderId, salesOrderDto);

        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build();
    }
}
