package com.paytakcode.inventorymanager.api.v1.util;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * Login User AuditorAware
 *
 * @Author 김태산
 * @Version 0.1.1
 * @Since 2023-05-19 오전 1:36
 */
@Component
public class LoginUserAuditorAware implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return Optional.empty();
        }
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails) {
            UserDetails user = (UserDetails)principal;
            return Optional.of(user.getUsername()); // CustomUserDetailsService에서 email로 변경하였으므로 email이 반환됨
        }
        return Optional.of("anonymous"); // 로그인 하지 않은 상태
    }
}

