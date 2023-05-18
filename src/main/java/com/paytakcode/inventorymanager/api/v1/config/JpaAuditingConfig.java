package com.paytakcode.inventorymanager.api.v1.config;

/**
 * JpaAuditing 설정 파일
 * @Author 김태산
 * @Version 0.1.0
 * @Since 2023-05-17 오후 10:27
 */

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class JpaAuditingConfig {

}
