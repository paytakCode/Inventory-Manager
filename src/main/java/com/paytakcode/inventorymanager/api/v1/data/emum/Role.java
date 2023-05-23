package com.paytakcode.inventorymanager.api.v1.data.emum;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Role Enum
 * @Author 김태산
 * @Version 0.1.2
 * @Since 2023-05-18 오후 3:04
 */
public enum Role {
	ROLE_ADMIN("관리자"),
	ROLE_WAIT("대기"),
	ROLE_MATERIAL("자재부"),
	ROLE_PRODUCTION("생산부"),
	ROLE_SALES("영업부");
	private final String roleName;

	Role(String roleName) {
		this.roleName = roleName;
	}

	@JsonCreator
	public static Role from(String roleName) {
		for (Role role : Role.values()) {
			if (role.getRoleName().equals(roleName)) {
				return role;
			}
		}
		return null;
	}

	@JsonValue
	public String getRoleName() {
		return roleName;
	}
}
