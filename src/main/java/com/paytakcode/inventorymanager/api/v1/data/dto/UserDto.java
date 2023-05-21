package com.paytakcode.inventorymanager.api.v1.data.dto;

import com.paytakcode.inventorymanager.api.v1.data.emum.Role;

import lombok.Builder;
import lombok.Data;

/**
 * User DTO
 * @Author 김태산
 * @Version 0.1.1
 * @Since 2023-05-18 오후 3:54
 */
@Data
@Builder
public class UserDto {
	private String email;
	private String name;
	private String tel;
	private String password;
	private Role role;
}
