package com.paytakcode.inventorymanager.api.v1.data.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;

/**
 * Role DTO
 * @Author 김태산
 * @Version 0.1.0
 * @Since 2023-05-23 오전 12:54
 */

@Data
public class RoleDto {

	@NotBlank
	String role;
}
