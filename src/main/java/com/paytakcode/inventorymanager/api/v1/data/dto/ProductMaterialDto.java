package com.paytakcode.inventorymanager.api.v1.data.dto;

import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Product Material DTO
 * @Author 김태산
 * @Version 0.1.0
 * @Since 2023-05-25 오전 9:05
 */

@Getter
@Setter
@Builder
@ToString
public class ProductMaterialDto {

	@NotNull
	private Long productId;

	@NotNull
	private Long materialId;

	@NotNull
	private Integer requiredQuantity;

}
