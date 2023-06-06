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
 * Supplier DTO
 * @Author 김태산
 * @Version 0.1.2
 * @Since 2023-05-24 오후 3:48
 */

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SupplierDto {

	@Nullable
	private Long id;

	@NotBlank
	private String companyName;

	@NotBlank
	private String loc;

	@NotBlank
	private String managerName;

	@NotBlank
	private String tel;
}
