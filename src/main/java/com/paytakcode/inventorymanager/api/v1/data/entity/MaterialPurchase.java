package com.paytakcode.inventorymanager.api.v1.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.springframework.lang.Nullable;

import com.paytakcode.inventorymanager.api.v1.data.emum.PurchaseStatus;
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
 * @Version 0.2.0
 * @Since 2023-05-21 오후 11:00
 */
@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class MaterialPurchase extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(updatable = false)
	private Long id;

	@NotNull
	@ManyToOne
	private UserEntity manager;

	@NotNull
	@ManyToOne
	private Material material;

	@Nullable
	@OneToOne(fetch = FetchType.LAZY)
	@ToString.Exclude
	private MaterialRequest materialRequest;

	@Nullable
	private String lotNo;

	@NotNull
	private Integer quantity;

	@NotNull
	private Integer price;

	@Nullable
	private String details;

	@NotNull
	@Enumerated(EnumType.STRING)
	private PurchaseStatus status;

	@NotNull
	private Boolean isDeleted;
}
