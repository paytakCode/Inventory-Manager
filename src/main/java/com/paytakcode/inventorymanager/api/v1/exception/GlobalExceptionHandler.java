package com.paytakcode.inventorymanager.api.v1.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Global Exception Handler
 * 전반적인 Exception 처리를 위한 Handler
 * @Author 김태산
 * @Version 0.1.0
 * @Since 2023-05-22 오후 12:10
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException e) {
		HttpStatus status = HttpStatus.FORBIDDEN;
		ErrorDetails errorDetails = new ErrorDetails(status.value(), e.getMessage());
		return new ResponseEntity<>(errorDetails, status);
	}
}
