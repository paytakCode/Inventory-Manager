package com.paytakcode.inventorymanager.api.v1.data.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ProductMaterial 복합키 클래스
 * @Author 김태산
 * @Version 0.1.0
 * @Since 2023-05-23 오전 11:17
 */
@Embeddable
@Data
@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class ProductMaterialId implements Serializable {

	@NotNull
	@ManyToOne
	public Product product;

	@NotNull
	@ManyToOne
	private Material material;
}
