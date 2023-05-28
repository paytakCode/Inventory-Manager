package com.paytakcode.inventorymanager.api.v1.config;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.paytakcode.inventorymanager.api.v1.util.JwtUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * JwtFilter
 * @Author 김태산
 * @Version 0.2.0
 * @Since 2023-05-23 오후 4:35
 */

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

	private final JwtUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
		FilterChain filterChain) throws ServletException, IOException {

		final String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);

		if (authorization == null || !authorization.startsWith("Bearer ")) {
			log.info("[doFilterInternal] Unauthorized User");
			filterChain.doFilter(request, response);
			return;
		}

		final String jwt = authorization.split(" ")[1];

		if (jwtUtil.isExpired(jwt)) {
			log.error("[doFilterInternal] Expired Token");
			filterChain.doFilter(request, response);
			return;
		}

		if (jwtUtil.isInvalidated(jwt)) {
			log.error("[doFilterInternal] Invalidated Token");
			filterChain.doFilter(request, response);
			return;
		}

		UsernamePasswordAuthenticationToken authenticationToken =
			new UsernamePasswordAuthenticationToken(jwtUtil.getEmail(jwt), null,
				Collections.singleton(new SimpleGrantedAuthority(jwtUtil.getRole(jwt).name())));

		authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		filterChain.doFilter(request, response);
	}
}
