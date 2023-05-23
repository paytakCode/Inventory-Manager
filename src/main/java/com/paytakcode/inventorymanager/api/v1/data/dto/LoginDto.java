package com.paytakcode.inventorymanager.api.v1.data.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;

/**
 * Login DTO
 * @Author 김태산
 * @Version 0.1.0
 * @Since 2023-05-22 오후 11:19
 */
@Data
public class LoginDto {

	@NotBlank
	@Email
	private String email;

	@NotBlank
	private String password;
}
