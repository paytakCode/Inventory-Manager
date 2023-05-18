package com.paytakcode.inventorymanager.api.v1.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

/**
 * 로그인 성공 Handler
 * @Author 김태산
 * @Version 0.1.0
 * @Since 2023-05-17 오후 11:43
 */
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	/**
	 * 로그인 성공시 메인페이지로 이동
	 * @param request the request which caused the successful authentication
	 * @param response the response
	 * @param authentication the <tt>Authentication</tt> object which was created during
	 * the authentication process.
	 * @throws IOException
	 * @throws ServletException
	 */
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
		Authentication authentication) throws IOException, ServletException {
		response.sendRedirect(request.getContextPath() + "/main");
	}
}
