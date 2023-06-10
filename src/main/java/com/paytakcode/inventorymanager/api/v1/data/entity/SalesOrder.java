package com.paytakcode.inventorymanager.api.v1.data.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.lang.Nullable;

import com.paytakcode.inventorymanager.api.v1.data.emum.OrderStatus;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Sales Entity
 * 판매 내역
 * @Author 김태산
 * @Version 0.1.2
 * @Since 2023-05-22 오전 12:14
 */
@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class SalesOrder extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(updatable = false)
	private Long id;

	@NotNull
	@ManyToOne
	private UserEntity manager;

	@NotNull
	@ManyToOne
	private Buyer buyer;

	@NotNull
	@ManyToOne
	private Product product;

	@NotNull
	private Integer quantity;

	@NotNull
	private Integer price;

	@NotNull
	private LocalDateTime dueDate;

	@Nullable
	private LocalDateTime completionDate;

	@NotNull
	@Enumerated(EnumType.STRING)
	private OrderStatus status;

	@NotNull
	private Boolean isDeleted;

}
