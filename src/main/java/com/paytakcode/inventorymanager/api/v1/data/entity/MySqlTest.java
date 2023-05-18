package com.paytakcode.inventorymanager.api.v1.data.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * MySql 연동 테스트를 위한 Entity입니다.
 * @Author 김태산
 * @Version 0.1.1
 * @Since 2023-05-17
 */
@Entity
@NoArgsConstructor
@Getter
@Setter
public class MySqlTest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
}
