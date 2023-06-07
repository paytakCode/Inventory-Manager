package com.paytakcode.inventorymanager.api.v1.data.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Product Material Content DTO
 * @Author 김태산
 * @Version 0.1.0
 * @Since 2023-06-07 오후 8:33
 */

@Getter
@Setter
@ToString
@Builder
public class ProductMaterialContentDto {

	@NotNull
	private ProductDto productDto;

	@NotNull
	private List<ProductMaterialDto> productMaterialDtoList;
}
