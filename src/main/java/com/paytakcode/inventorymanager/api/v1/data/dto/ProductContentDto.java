package com.paytakcode.inventorymanager.api.v1.data.dto;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Product Content DTO
 * @Author 김태산
 * @Version 0.1.0
 * @Since 2023-06-06 오후 10:07
 */

@Getter
@Setter
@ToString
public class ProductContentDto extends ProductDto {

	@NotNull
	Integer currentQuantity;

	@NotNull
	Integer inProductionQuantity;

	@NotNull
	Integer plannedOutboundQuantity;

	@NotNull
	Integer actualQuantity;
}
