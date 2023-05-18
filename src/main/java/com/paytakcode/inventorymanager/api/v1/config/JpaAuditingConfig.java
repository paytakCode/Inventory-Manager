package com.paytakcode.inventorymanager.api.v1.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Spring Data JPA Auditing Config
 *
 * @Author 김태산
 * @Version 0.1.0
 * @Since 2023-05-19 오전 1:32
 */

@Configuration
@EnableJpaAuditing
public class JpaAuditingConfig {
}
