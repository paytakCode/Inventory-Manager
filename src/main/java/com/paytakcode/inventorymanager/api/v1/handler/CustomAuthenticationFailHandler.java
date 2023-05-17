package com.paytakcode.inventorymanager.api.v1.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

/**
 * 로그인 실패 Handler
 * @Author 김태산
 * @Version 0.1.0
 * @Since 2023-05-17 오후 11:39
 */
@Component
public class CustomAuthenticationFailHandler implements AuthenticationFailureHandler {

	/**
	 * 로그인 실패시 로그인 페이지로 이동
	 * @param request the request during which the authentication attempt occurred.
	 * @param response the response.
	 * @param exception the exception which was thrown to reject the authentication
	 * request.
	 * @throws IOException
	 * @throws ServletException
	 */
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
		AuthenticationException exception) throws IOException, ServletException {
		response.sendRedirect(request.getContextPath() + "/login");
	}
}
