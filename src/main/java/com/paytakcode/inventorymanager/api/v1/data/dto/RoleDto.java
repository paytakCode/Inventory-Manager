package com.paytakcode.inventorymanager.api.v1.data.dto;

import javax.validation.constraints.NotNull;

import com.paytakcode.inventorymanager.api.v1.data.emum.Role;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Role DTO
 * @Author 김태산
 * @Version 0.1.0
 * @Since 2023-05-23 오전 12:54
 */

@Getter
@Setter
@ToString
public class RoleDto {

	@NotNull
	Role role;
}
