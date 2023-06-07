package com.paytakcode.inventorymanager.api.v1.data.dto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Product Material DTO
 * @Author 김태산
 * @Version 0.2.1
 * @Since 2023-05-25 오전 9:05
 */

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProductMaterialDto {

	@NotNull
	private ProductMaterialIdDto productMaterialIdDto;

	@NotNull
	private Integer requiredQuantity;

}
