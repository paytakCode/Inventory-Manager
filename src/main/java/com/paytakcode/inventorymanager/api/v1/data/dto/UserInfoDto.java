package com.paytakcode.inventorymanager.api.v1.data.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.paytakcode.inventorymanager.api.v1.data.emum.Role;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * User Info DTO
 * @Author 김태산
 * @Version 0.1.0
 * @Since 2023-05-26 오후 12:24
 */

@Getter
@Setter
@Builder
public class UserInfoDto {

    @NotNull
    private Long id;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String name;

    @NotBlank
    private String tel;

    @NotNull
    private Role role;
}
