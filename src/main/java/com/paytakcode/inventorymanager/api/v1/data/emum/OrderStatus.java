package com.paytakcode.inventorymanager.api.v1.data.emum;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Sales Status Enum
 * 판매 상태 목록
 * @Author 김태산
 * @Version 0.1.0
 * @Since 2023-05-22 오전 12:25
 */
public enum OrderStatus {
	ORDER_CONFIRMED("발주"),
	READY_SHIPMENT("출고대기"),
	SHIPPED("배송중"),
	DELIVERED("도착"),
	COMPLETED("처리완료"),
	CANCELED("발주취소");

	private final String statusName;

	OrderStatus(String statusName) {
		this.statusName = statusName;
	}

	@JsonCreator
	public static OrderStatus from(String statusName) {
		for (OrderStatus orderStatus : OrderStatus.values()) {
			if (orderStatus.getStatusName().equals(statusName)) {
				return orderStatus;
			}
		}
		return null;
	}

	@JsonValue
	public String getStatusName() {
		return statusName;
	}
}
