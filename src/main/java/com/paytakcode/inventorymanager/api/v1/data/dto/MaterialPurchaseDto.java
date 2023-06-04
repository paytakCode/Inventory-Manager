package com.paytakcode.inventorymanager.api.v1.data.dto;

import javax.validation.constraints.Min;

import org.springframework.lang.Nullable;

import com.paytakcode.inventorymanager.api.v1.data.emum.PurchaseStatus;
import com.sun.istack.NotNull;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Material Purchase DTO
 * @Author 김태산
 * @Version 0.2.0
 * @Since 2023-05-24 오후 10:24
 */

@Getter
@Setter
@Builder
@ToString
public class MaterialPurchaseDto {

	@Nullable
	private Long id;

	@NotNull
	private MaterialDto materialDto;

	@NotNull
	private UserInfoDto managerDto;

	@Nullable
	private MaterialRequestDto materialRequestDto;

	@Nullable
	private String details;

	@Nullable
	private String lotNo;

	@NotNull
	@Min(0)
	private Integer price;

	@NotNull
	@Min(1)
	private Integer quantity;

	@Nullable
	private PurchaseStatus status;
}
