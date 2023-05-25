package com.paytakcode.inventorymanager.api.v1.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.paytakcode.inventorymanager.api.v1.data.dto.UserInfoDto;
import com.paytakcode.inventorymanager.api.v1.data.entity.UserEntity;

/**
 * User Util
 * @Author 김태산
 * @Version 0.1.0
 * @Since 2023-05-26 오후 12:36
 */
public class UserUtil {

    private UserUtil() {

    }

    public static String getCurrentUserEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }

        return (String)authentication.getPrincipal();
    }
}
