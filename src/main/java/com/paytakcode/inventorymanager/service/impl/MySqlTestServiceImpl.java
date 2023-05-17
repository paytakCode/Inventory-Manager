package com.paytakcode.inventorymanager.service.impl;

import com.paytakcode.inventorymanager.data.dao.MySqlTestDao;
import com.paytakcode.inventorymanager.data.dto.MySqlTestDto;
import com.paytakcode.inventorymanager.data.entity.MySqlTest;
import com.paytakcode.inventorymanager.service.MySqlTestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * MySql 연동 테스트를 위한 Service 구현체입니다.
 * @Author 김태산
 * @Version 0.0.1
 * @Since 2023-05-17
 */
@Service
@RequiredArgsConstructor
public class MySqlTestServiceImpl implements MySqlTestService {

    private final MySqlTestDao mySqlTestDao;

    /**
     * 매개변수로 받은 이름을 DTO에 저장하고 DAO에게 전달합니다.
     * @param name 저장할 이름
     * @return 저장에 성공한 DTO
     */
    @Override
    public MySqlTestDto saveMySqlTest(String name){
        MySqlTestDto mySqlTestDto = new MySqlTestDto();
        mySqlTestDto.setName(name);
        MySqlTest savedMySqlTest = mySqlTestDao.saveMySqlTest(mySqlTestDto);
        MySqlTestDto savedMySqlTestDto = new MySqlTestDto();
        savedMySqlTestDto.setName(savedMySqlTest.getName());
        return savedMySqlTestDto;
    }
}
