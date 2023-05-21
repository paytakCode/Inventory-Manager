package com.paytakcode.inventorymanager.api.v1.data.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Product Material Entity
 * 생산의 필요한 자재 정보 테이블
 * @Author 김태산
 * @Version 0.1.0
 * @Since 2023-05-21 오후 9:02
 */
@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class ProductMaterial extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@ManyToOne
	private Product product;

	@NotNull
	@ManyToOne
	private Material material;

	@NotNull
	private Integer requiredQuantity;
}
