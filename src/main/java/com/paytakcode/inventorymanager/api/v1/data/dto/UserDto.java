package com.paytakcode.inventorymanager.api.v1.data.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.paytakcode.inventorymanager.api.v1.util.PasswordMaskingUtil;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * User DTO
 * @Author 김태산
 * @Version 0.1.2
 * @Since 2023-05-18 오후 3:54
 */
@Getter
@Setter
@Builder
public class UserDto {

	@NotBlank
	@Email
	private String email;

	@NotBlank
	private String name;

	@NotBlank
	private String tel;

	@NotBlank
	private String password;

	@Override
	public String toString() {
		return PasswordMaskingUtil.maskedToString(this);
	}
}
