package com.paytakcode.inventorymanager.api.v1.data.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Material Purchase Entity
 * 자재 구매 Table
 * @Author 김태산
 * @Version 0.1.0
 * @Since 2023-05-21 오후 11:00
 */
@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class MaterialPurchase {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@ManyToOne
	private UserEntity manager;

	@NotNull
	@OneToOne(mappedBy = "materialPurchase")
	private MaterialRequest request;

	@NotNull
	private Integer purchaseQuantity;

	@NotNull
	private Integer purchasePrice;

	private String details;
}
