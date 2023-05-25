package com.paytakcode.inventorymanager.api.v1.data.dto;

import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Supplier Id DTO
 * @Author 김태산
 * @Version 0.1.0
 * @Since 2023-05-24 오후 5:49
 */

@Getter
@Setter
@ToString
public class SupplierIdDto {

    @NotNull
    private Long supplierId;
}
