package com.paytakcode.inventorymanager.api.v1.service;

import com.paytakcode.inventorymanager.api.v1.data.dto.MySqlTestDto;

/**
 * MySql 연동 테스트를 위한 Service Interface 입니다.
 * @Author 김태산
 * @Version 0.1.0
 * @Since 2023-05-17
 */
public interface MySqlTestService {
    MySqlTestDto saveMySqlTest(String name);
}
