package com.paytakcode.inventorymanager.api.v1.data.dto;

import lombok.Data;

/**
 * User DTO
 * @Author 김태산
 * @Version 0.1.0
 * @Since 2023-05-18 오전 12:30
 */

@Data
public class UserDto {
	private String email;
	private String password;
}
