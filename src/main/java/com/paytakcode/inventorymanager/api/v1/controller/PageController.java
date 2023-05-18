package com.paytakcode.inventorymanager.api.v1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Page 이동을 위한 Controller
 * @Author 김태산
 * @Version 0.1.0
 * @Since 2023-05-17 오후 11:58
 */

@Controller
@RequestMapping("/api/v1")
public class PageController {

	@GetMapping("/login")
	public String loginPage(){
		return "login";
	}
}
