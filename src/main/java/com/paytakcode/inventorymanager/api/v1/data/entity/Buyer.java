package com.paytakcode.inventorymanager.api.v1.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Buyer Entity
 * @Author 김태산
 * @Version 0.1.1
 * @Since 2023-05-22 오전 12:11
 */
@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class Buyer extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(updatable = false)
	private Long id;

	@NotNull
	private String companyName;

	@NotNull
	private String managerName;

	@NotNull
	private String tel;

	@NotNull
	private String loc;

}
