package com.paytakcode.inventorymanager.api.v1.data.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.Hibernate;

import com.paytakcode.inventorymanager.api.v1.data.emum.Role;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * UserEntity
 * Security User랑 구분하기 위해 접미사 Entity 추가
 *
 * @Author 김태산
 * @Version 0.1.2
 * @Since 2023-05-18 오후 2:55
 */

@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Table(name = "user")
public class UserEntity extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Column(unique = true)
	private String email;

	@NotNull
	private String name;

	@NotNull
	private String tel;

	@NotNull
	private String password;

	@NotNull
	@Enumerated(EnumType.STRING)
	private Role role;

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o))
			return false;
		UserEntity that = (UserEntity)o;
		return getId() != null && Objects.equals(getId(), that.getId());
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
