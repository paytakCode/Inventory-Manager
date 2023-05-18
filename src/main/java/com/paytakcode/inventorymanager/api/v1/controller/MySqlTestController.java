package com.paytakcode.inventorymanager.api.v1.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.paytakcode.inventorymanager.api.v1.data.dto.MySqlTestDto;
import com.paytakcode.inventorymanager.api.v1.service.MySqlTestService;

import lombok.RequiredArgsConstructor;

/**
 * MySql 연동 테스트를 위한 Controller입니다.
 * @Author 김태산
 * @Version 0.1.2
 * @Since 2023-05-17
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class MySqlTestController {

    private final MySqlTestService mySqlTestService;

    /**
     * Test Table에 Url로 받은 name을 저장하는 서비스를 호출하는 메서드입니다.
     * @param name 저장할 이름
     * @return 저장된 MySqlTestDto 정보
     */
    @PostMapping("/mysql-test")
    public String mySqlTestAdd(@RequestParam String name){
        MySqlTestDto insertedMySqlTestDto = mySqlTestService.addMySqlTest(name);
        return insertedMySqlTestDto.toString();
    }

}
