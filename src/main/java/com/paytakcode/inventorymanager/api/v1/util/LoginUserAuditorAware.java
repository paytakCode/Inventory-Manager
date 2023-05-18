package com.paytakcode.inventorymanager.api.v1.util;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Login User AuditorAware
 *
 * @Author 김태산
 * @Version 0.1.0
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
        User user = (User) authentication.getPrincipal();
        return Optional.of(user.getUsername()); // UserDetailsService의 username을 email로 사용하고 있으므로 email 정보를 반환함
    }
}
