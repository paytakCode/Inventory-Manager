package com.paytakcode.inventorymanager.data.dao;

import com.paytakcode.inventorymanager.data.dto.MySqlTestDto;
import com.paytakcode.inventorymanager.data.entity.MySqlTest;

/**
 * MySql 연동 테스트를 위한 DAO Interface입니다.
 * @Author 김태산
 * @Version 0.0.1
 * @Since 2023-05-17
 */
public interface MySqlTestDao {

    MySqlTest saveMySqlTest(MySqlTestDto mySqlTestDto);
}
