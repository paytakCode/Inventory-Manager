package com.paytakcode.inventorymanager.api.v1.data.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import org.springframework.lang.Nullable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Material Request DTO
 * @Author 김태산
 * @Version 0.4.1
 * @Since 2023-05-24 오후 12:28
 */

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MaterialRequestDto {

	@Nullable
	private Long id;

	@NotNull
	private MaterialDto materialDto;

	@NotNull
	private UserInfoDto requesterDto;

	@NotNull
	private Integer quantity;

	@Nullable
	private String details;

	@Nullable
	private LocalDateTime requestDate;

	@Nullable
	private MaterialPurchaseDto materialPurchaseDto;
}
