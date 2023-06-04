package com.paytakcode.inventorymanager.api.v1.data.dto;

import javax.validation.constraints.NotBlank;

import org.springframework.lang.Nullable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Material DTO
 * @Author 김태산
 * @Version 0.2.0
 * @Since 2023-05-24 오전 11:40
 */

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MaterialDto {

	@Nullable
	private Long id;

	@NotBlank
	private String name;

	@NotBlank
	private String spec;

	@Nullable
	private String details;

	@Nullable
	private SupplierDto supplierDto;
}
