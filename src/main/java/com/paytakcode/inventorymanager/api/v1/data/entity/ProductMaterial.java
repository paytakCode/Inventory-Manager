package com.paytakcode.inventorymanager.api.v1.data.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

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
 * @Version 0.1.2
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

	@EmbeddedId
	@Column(updatable = false)
	private ProductMaterialId id;

	@NotNull
	private Integer requiredQuantity;

	@NotNull
	private Boolean isDeleted;
}
