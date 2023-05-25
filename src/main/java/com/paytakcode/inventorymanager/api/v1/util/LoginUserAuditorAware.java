package com.paytakcode.inventorymanager.api.v1.util;

import java.util.Objects;
import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * Login User AuditorAware
 *
 * @Author 김태산
 * @Version 0.1.1
 * @Since 2023-05-19 오전 1:36
 */

@Component
@Slf4j
public class LoginUserAuditorAware implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || !authentication.isAuthenticated()) {
			return Optional.empty();
		}

		String auditorEmail = (String)authentication.getPrincipal();

		log.info("[getCurrentAuditor] auditorEmail: {}", auditorEmail);

		return Optional.of(Objects.requireNonNullElse(auditorEmail, "anonymous"));

	}
}

