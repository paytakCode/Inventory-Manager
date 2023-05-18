package com.paytakcode.inventorymanager.api.v1.data.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.paytakcode.inventorymanager.api.v1.data.dto.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * UserEntity
 * Security User랑 구분하기 위해 접미사 Entity 추가
 * @Author 김태산
 * @Version 0.1.0
 * @Since 2023-05-18 오후 2:55
 */

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String email;
	private String name;
	private String password;

	@Enumerated(EnumType.STRING)
	private Role role;

}
