package com.paytakcode.inventorymanager.api.v1.data.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.paytakcode.inventorymanager.api.v1.util.PasswordMaskingUtil;

import lombok.Getter;
import lombok.Setter;

/**
 * Login DTO
 * @Author 김태산
 * @Version 0.2.0
 * @Since 2023-05-22 오후 11:19
 */
@Getter
@Setter
public class LoginDto {

	@NotBlank
	@Email
	private String email;

	@NotBlank
	private String password;

	@Override
	public String toString() {
		return PasswordMaskingUtil.maskedToString(this);
	}
}
