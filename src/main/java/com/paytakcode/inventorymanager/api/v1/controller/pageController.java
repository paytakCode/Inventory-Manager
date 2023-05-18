package com.paytakcode.inventorymanager.api.v1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * PageController
 * @Author 김태산
 * @Version 0.1.0
 * @Since 2023-05-18 오후 1:27
 */

@Controller
@RequestMapping("/api/v1")
public class pageController {

	@GetMapping("/main")
	public String mainPage() {
		return "main";
	}

	@GetMapping("/intro")
	public String introPage() {
		return "intro";
	}

	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}

	@GetMapping("/signup")
	public String signupPage() {
		return "signup";
	}
}
