package com.paytakcode.inventorymanager.api.v1.data.emum;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Purchase Status Enum
 * Purchase Material Table의 Status에 기입되는 상태 목록
 * @Author 김태산
 * @Version 0.1.0
 * @Since 2023-05-24 오후 11:24
 */
public enum PurchaseStatus {
	ACCEPTED("구매예정"),
	ORDERED("주문완료"),
	RECEIVED("입고"),
	COMPLETED("완료"),
	CANCELED("취소");

	private final String statusName;

	PurchaseStatus(String statusName) {
		this.statusName = statusName;
	}

	@JsonCreator
	public static PurchaseStatus from(String statusName) {
		for (PurchaseStatus purchaseStatus : PurchaseStatus.values()) {
			if (purchaseStatus.getStatusName().equals(statusName)) {
				return purchaseStatus;
			}
		}
		return null;
	}

	@JsonValue
	public String getStatusName() {
		return statusName;
	}
}
