package com.paytakcode.inventorymanager.controller;


import com.paytakcode.inventorymanager.data.dto.MySqlTestDto;
import com.paytakcode.inventorymanager.service.MySqlTestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * MySql 연동 테스트를 위한 Controller입니다.
 * @Author 김태산
 * @Version 0.0.1
 * @Since 2023-05-17
 */
@RestController
@RequiredArgsConstructor
public class MySqlTestController {

    private final MySqlTestService mySqlTestService;

    /**
     * Test Table에 Url로 받은 name을 저장하는 서비스를 호출하는 메서드입니다.
     * 연결 여부만 확인하기 위해 GetMapping을 사용하였습니다.
     * @param name 저장할 이름
     * @return 저장된 MySqlTestDto 정보
     */
    @GetMapping("/mysql-test")
    public String mySqlTestInsert(@RequestParam String name){
        MySqlTestDto insertedMySqlTestDto = mySqlTestService.saveMySqlTest(name);
        return insertedMySqlTestDto.toString();
    }

}
