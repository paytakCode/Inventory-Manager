package com.paytakcode.inventorymanager.api.v1.data.dto;

import lombok.Data;

/**
 * Register DTO
 * @Author 김태산
 * @Version 0.1.0
 * @Since 2023-05-18 오후 3:49
 */

@Data
public class RegisterDto {

	private String email;
	private String name;
	private String password;
	private Role role;

}
