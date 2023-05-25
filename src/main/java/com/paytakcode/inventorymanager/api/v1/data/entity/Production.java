package com.paytakcode.inventorymanager.api.v1.data.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.lang.Nullable;

import com.paytakcode.inventorymanager.api.v1.data.emum.ProductionStatus;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Production Entity
 * 생산 내역 Table
 * @Author 김태산
 * @Version 0.1.1
 * @Since 2023-05-21 오후 11:34
 */
@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class Production extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Nullable
	private String lotNo;

	@NotNull
	@ManyToOne
	private UserEntity manager;

	@NotNull
	@ManyToOne
	private Product product;

	@NotNull
	private Integer quantity;

	@NotNull
	@Enumerated(EnumType.STRING)
	private ProductionStatus status;

	@NotNull
	private LocalDateTime targetDate;

	@Nullable
	private LocalDateTime completionDate;

	@Nullable
	private String details;
}
