package com.paytakcode.inventorymanager.api.v1.data.entity;

import com.paytakcode.inventorymanager.api.v1.data.dto.Role;
import lombok.*;

import javax.persistence.*;

/**
 * UserEntity
 * Security User랑 구분하기 위해 접미사 Entity 추가
 *
 * @Author 김태산
 * @Version 0.1.1
 * @Since 2023-05-18 오후 2:55
 */

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class UserEntity extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private String email;
	private String name;
	private String password;

	@Enumerated(EnumType.STRING)
	private Role role;

}
