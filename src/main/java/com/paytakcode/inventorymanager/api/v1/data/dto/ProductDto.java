package com.paytakcode.inventorymanager.api.v1.data.dto;

import javax.validation.constraints.NotBlank;

import org.springframework.lang.Nullable;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Product DTO
 * @Author 김태산
 * @Version 0.1.1
 * @Since 2023-05-25 오후 1:51
 */

@Getter
@Setter
@Builder
@ToString
public class ProductDto {

	@Nullable
	private Long id;

	@NotBlank
	private String name;

	@Nullable
	private String spec;

	@Nullable
	private String details;
}
