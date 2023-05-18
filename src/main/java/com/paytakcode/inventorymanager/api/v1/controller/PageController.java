package com.paytakcode.inventorymanager.api.v1.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Page Controller
 *
 * @Author 김태산
 * @Version 0.1.1
 * @Since 2023-05-18 오후 1:27
 */

@Slf4j
@Controller
@RequestMapping("/api/v1")
public class PageController {

	@GetMapping("/intro")
	public String introPage() {
		log.info("[introPage] page Load");
		return "intro";
	}

	@GetMapping("/main")
	public String mainPage() {
		log.info("[mainPage] page Load");
		return "main";
	}

	@GetMapping("/login")
	public String loginPage() {
		log.info("[loginPage] page Load");
		return "login";
	}

	@GetMapping("/register")
	public String registerPage() {
		log.info("[registerPage] page Load");
		return "register";
	}
}
