package com.paytakcode.inventorymanager.api.v1.data.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * ProductMaterial 복합키 클래스
 * @Author 김태산
 * @Version 0.1.0
 * @Since 2023-05-23 오전 11:17
 */
@Embeddable
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class ProductMaterialId implements Serializable {

	@NotNull
	@ManyToOne
	@JoinColumn(name = "product_id")
	public Product product;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "material_id")
	private Material material;
}
