package com.paytakcode.inventorymanager.api.v1.exception;

import javax.persistence.EntityNotFoundException;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;

/**
 * Global Exception Handler
 * 전반적인 Exception 처리를 위한 Handler
 * @Author 김태산
 * @Version 0.1.2
 * @Since 2023-05-22 오후 12:10
 */

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<Void> handleAccessDeniedException(AccessDeniedException e) {
		log.error("[handleAccessDeniedException] {}", e.getMessage());
		return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(AuthenticationException.class)
	public ResponseEntity<Void> handleAuthenticationException(AuthenticationException e) {
		log.error("[handleAuthenticationException] {}", e.getMessage());
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(DataAccessException.class)
	public ResponseEntity<Void> handleDataAccessException(DataAccessException e) {
		log.error("[handleDataAccessException] {}", e.getMessage());
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<Void> handleUsernameNotFoundException(UsernameNotFoundException e) {
		log.error("[handleUsernameNotFoundException] {}", e.getMessage());
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Void> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		log.error("[handleMethodArgumentNotValidException] {}", e.getMessage());
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<Void> handleNullPointerException(NullPointerException e) {
		log.error("[handleNullPointerException] {}", e.getMessage());
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<Void> handleEntityNotFoundException(EntityNotFoundException e) {
		log.error("[handleEntityNotFoundException] {}", e.getMessage());
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Void> handleUnExpectedException(Exception e) {
		log.error("[handleUnExpectedException] {}", e.getMessage());
		return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
	}
}
