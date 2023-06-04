package com.paytakcode.inventorymanager.api.v1.data.dto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Material Content DTO
 * @Author 김태산
 * @Version 0.1.0
 * @Since 2023-06-04 오후 7:10
 */

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MaterialContentDto extends MaterialDto {

	@NotNull
	Integer currentQuantity;

	@NotNull
	Integer expectedInboundQuantity;

	@NotNull
	Integer plannedConsumptionQuantity;

	@NotNull
	Integer actualQuantity;
}
