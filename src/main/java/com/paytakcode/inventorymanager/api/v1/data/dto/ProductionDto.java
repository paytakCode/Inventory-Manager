package com.paytakcode.inventorymanager.api.v1.data.dto;


import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import org.springframework.lang.Nullable;

import com.paytakcode.inventorymanager.api.v1.data.emum.ProductionStatus;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Production DTO
 * @Author 김태산
 * @Version 0.1.2
 * @Since 2023-05-25 오후 5:06
 */

@Getter
@Setter
@ToString
@Builder
public class ProductionDto {

	@Nullable
	private Long id;

	@NotNull
	private Long productId;

	@NotNull
	private Long managerId;

	@Nullable
	private String lotNo;

	@Nullable
	private String details;

	@NotNull
	private Integer quantity;

	@NotNull
	private LocalDateTime targetDate;

	@Nullable
	private ProductionStatus status;
}
