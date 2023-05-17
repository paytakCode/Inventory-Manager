package com.paytakcode.inventorymanager.data.dao.impl;

import com.paytakcode.inventorymanager.data.dao.MySqlTestDao;
import com.paytakcode.inventorymanager.data.dto.MySqlTestDto;
import com.paytakcode.inventorymanager.data.entity.MySqlTest;
import com.paytakcode.inventorymanager.data.repository.MySqlTestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * MySql 연동 테스트를 위한 DAO 구현체입니다.
 * @Author 김태산
 * @Version 0.0.1
 * @Since 2023-05-17
 */
@Repository
@RequiredArgsConstructor
public class MySqlTestDaoImpl implements MySqlTestDao {

    private final MySqlTestRepository mySqlTestRepository;

    /**
     * 매개 변수로 받은 DTO를 이용해 DB에 저장합니다.
     * @param mySqlTestDto 저장할 MySqlTest DTO
     * @return 저장된 MySqlTest Entity
     */
    @Override
    public MySqlTest saveMySqlTest(MySqlTestDto mySqlTestDto){
        MySqlTest mySqlTest = new MySqlTest();
        mySqlTest.setName(mySqlTestDto.getName());
        return mySqlTestRepository.save(mySqlTest);
    }
}
