package com.paytakcode.inventorymanager.api.v1.data.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * Auditing을 이용한 생성, 수정 이력을 위한 공용 Entity
 *
 * @Author 김태산
 * @Version 0.1.1
 * @Since 2023-05-17 오후 10:11
 */

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

	@CreatedDate
	@Column(updatable = false)
	LocalDateTime createdDate;

	@LastModifiedDate
	LocalDateTime modifiedDate;

	@CreatedBy
	@Column(updatable = false)
	String createdBy;

	@LastModifiedBy
	String modifiedBy;
}
