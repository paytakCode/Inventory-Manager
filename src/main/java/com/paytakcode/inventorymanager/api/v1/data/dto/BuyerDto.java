package com.paytakcode.inventorymanager.api.v1.data.dto;

import javax.validation.constraints.NotBlank;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Buyer DTO
 * @Author 김태산
 * @Version 0.1.0
 * @Since 2023-05-26 오후 3:23
 */

@Getter
@Setter
@ToString
@Builder
public class BuyerDto {

    @NotBlank
    private String companyName;

    @NotBlank
    private String managerName;

    @NotBlank
    private String loc;

    @NotBlank
    private String tel;
}
