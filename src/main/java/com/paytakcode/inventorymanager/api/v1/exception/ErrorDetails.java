package com.paytakcode.inventorymanager.api.v1.exception;

import java.time.LocalDateTime;

/**
 * ErrorDetails Class
 * 에러 정보를 담을 객체
 * @Author 김태산
 * @Version 0.1.0
 * @Since 2023-05-22 오후 12:14
 */

public class ErrorDetails {
	private LocalDateTime timestamp;
	private final int statusCode;
	private final String message;

	public ErrorDetails(int statusCode, String message) {
		this.statusCode = statusCode;
		this.message = message;
		this.timestamp = LocalDateTime.now();
	}
}
