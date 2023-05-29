package com.paytakcode.inventorymanager.api.v1.data.dto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Required Quantity DTO
 * @Author 김태산
 * @Version 0.1.0
 * @Since 2023-05-29 오후 11:09
 */

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RequiredQuantityDto {

	@NotNull
	private Integer requiredQuantity;
}
