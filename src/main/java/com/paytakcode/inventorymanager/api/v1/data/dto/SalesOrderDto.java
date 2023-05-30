package com.paytakcode.inventorymanager.api.v1.data.dto;

import java.time.LocalDateTime;

import org.springframework.lang.Nullable;

import com.paytakcode.inventorymanager.api.v1.data.emum.OrderStatus;
import com.sun.istack.NotNull;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Sales DTO
 * @Author 김태산
 * @Version 0.1.2
 * @Since 2023-05-27 오후 9:23
 */

@Getter
@Setter
@Builder
@ToString
public class SalesOrderDto {

	@Nullable
	private Long id;

	@NotNull
	private Long productId;

	@NotNull
	private Integer quantity;

	@NotNull
	private Long managerId;

	@NotNull
	private Long buyerId;

	@NotNull
	private LocalDateTime dueDate;

	@Nullable
	private LocalDateTime completionDate;

	@Nullable
	private OrderStatus status;
}
