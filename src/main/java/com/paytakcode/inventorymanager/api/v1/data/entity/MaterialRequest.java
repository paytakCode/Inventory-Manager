package com.paytakcode.inventorymanager.api.v1.data.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.paytakcode.inventorymanager.api.v1.data.emum.RequestStatus;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Material Request Entity
 * 생산부에서 자재 요청한 내역을 저장하는 테이블
 * @Author 김태산
 * @Version 0.1.0
 * @Since 2023-05-21 오후 10:39
 */
@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class MaterialRequest extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@ManyToOne
	private Material material;

	@NotNull
	@ManyToOne
	private UserEntity requester;

	@NotNull
	private Integer requestQuantity;

	private String requestInfo;

	@OneToOne
	private MaterialPurchase materialPurchase;

	@NotNull
	@Enumerated(EnumType.STRING)
	private RequestStatus status;

}
