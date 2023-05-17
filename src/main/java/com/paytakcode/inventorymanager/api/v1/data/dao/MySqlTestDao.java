package com.paytakcode.inventorymanager.api.v1.data.dao;

import com.paytakcode.inventorymanager.api.v1.data.dto.MySqlTestDto;
import com.paytakcode.inventorymanager.api.v1.data.entity.MySqlTest;

/**
 * MySql 연동 테스트를 위한 DAO Interface입니다.
 * @Author 김태산
 * @Version 0.1.0
 * @Since 2023-05-17
 */
public interface MySqlTestDao {

    MySqlTest saveMySqlTest(MySqlTestDto mySqlTestDto);
}
