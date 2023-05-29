package com.paytakcode.inventorymanager.api.v1.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
 * @Version 0.3.0
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

    @GetMapping("/sales/buyers/{buyerId}")
    public ResponseEntity<BuyerDto> buyerById(@PathVariable Long buyerId) {
        log.info("[buyerById] param - buyerId: {}", buyerId);

        BuyerDto buyerDto = salesService.getBuyerById(buyerId);

        log.info("[buyerById] return - HttpStatus.OK(200), buyerDto: {}", buyerDto);
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(buyerDto);
    }

    @PutMapping("/sales/buyers/{buyerId}")
    public ResponseEntity<Void> buyerUpdate(@PathVariable Long buyerId,
        @Valid @RequestBody BuyerDto buyerDto) {
        log.info("[buyerUpdate] param - buyerId: {}, buyerDto: {}", buyerId, buyerDto);

        salesService.updateBuyer(buyerId, buyerDto);

        log.info("[buyerUpdate] return - HttpStatus.NO_CONTENT(204)");
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build();
    }

    @DeleteMapping("/sales/buyers/{buyerId}")
    public ResponseEntity<Void> buyerDeleteById(@PathVariable Long buyerId) {
        log.info("[buyerDeleteById] param - buyerId: {}", buyerId);

        salesService.deleteBuyerById(buyerId);

        log.info("[buyerDeleteById] return - HttpStatus.NO_CONTENT(204)");
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build();
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

    @GetMapping("/sales/sales-orders/{salesOrderId}")
    public ResponseEntity<SalesOrderDto> salesOrderById(@PathVariable Long salesOrderId) {
        log.info("[salesOrderById] param - salesOrderId: {}", salesOrderId);

        SalesOrderDto salesOrderDto = salesService.getSalesOrderById(salesOrderId);

        log.info("[salesOrderById] return - HttpStatus.OK(200), salesOrderDto: {}", salesOrderDto);
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(salesOrderDto);
    }

    @PutMapping("/sales/sales-orders/{salesOrderId}")
    public ResponseEntity<String> salesOrderUpdate(@PathVariable Long salesOrderId,
        @RequestBody @Valid SalesOrderDto salesOrderDto) {
        log.info("[salesOrderUpdate] param - salesOrderId: {}, salesOrderDto: {}", salesOrderId, salesOrderDto);

        salesService.updateSalesOrder(salesOrderId, salesOrderDto);

        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build();
    }

    @DeleteMapping("/sales/sales-orders/{salesOrderId}")
    public ResponseEntity<Void> salesOrderDeleteById(@PathVariable Long salesOrderId) {
        log.info("[salesOrderDeleteById] param - salesOrderId: {}", salesOrderId);

        salesService.deleteSalesOrderById(salesOrderId);

        log.info("[salesOrderDeleteById] return - HttpStatus.NO_CONTENT(204)");
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build();
    }
}
