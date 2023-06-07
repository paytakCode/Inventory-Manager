package com.paytakcode.inventorymanager.api.v1.data.dto;

import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Product Material Id Dto
 * @Author 김태산
 * @Version 0.2.0
 * @Since 2023-05-29 오후 9:42
 */

@Getter
@Setter
@Builder
@ToString
public class ProductMaterialIdDto {

	@NotNull
	private ProductDto productDto;

	@NotNull
	private MaterialDto materialDto;
}
