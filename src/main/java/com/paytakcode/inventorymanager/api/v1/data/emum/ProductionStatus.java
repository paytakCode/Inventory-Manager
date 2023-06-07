package com.paytakcode.inventorymanager.api.v1.data.emum;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Production Status Enum
 * 생산 상태 목록
 * @Author 김태산
 * @Version 0.1.1
 * @Since 2023-05-21 오후 11:49
 */
public enum ProductionStatus {
	PLANNED("생산예정"),
	INPRODUCTION("생산중"),
	COMPLETED("생산완료"),
	CANCELED("취소");

	private final String statusName;

	ProductionStatus(String statusName) {
		this.statusName = statusName;
	}

	@JsonCreator
	public static ProductionStatus from(String statusName) {
		for (ProductionStatus productionStatus : ProductionStatus.values()) {
			if (productionStatus.getStatusName().equals(statusName)) {
				return productionStatus;
			}
		}
		return null;
	}

	@JsonValue
	public String getStatusName() {
		return statusName;
	}
}
