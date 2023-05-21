package com.paytakcode.inventorymanager.api.v1.data.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.paytakcode.inventorymanager.api.v1.data.emum.SalesStatus;
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
 * @Version 0.1.0
 * @Since 2023-05-22 오전 12:14
 */
@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class Sales extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	private Date regDate;

	@NotNull
	private Date dueDate;

	@NotNull
	@Enumerated(EnumType.STRING)
	private SalesStatus status;

}
