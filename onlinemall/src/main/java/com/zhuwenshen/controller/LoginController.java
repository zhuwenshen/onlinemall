package com.zhuwenshen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.zhuwenshen.model.custom.JsonResult;
import com.zhuwenshen.service.UserService;

@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;
	

	@GetMapping("/login")
	public String login() {
		return "/login/login";
	}
	
	
	@PostMapping("/login")
	public JsonResult login(String loginId, String password,String ip) {
		return userService.login(loginId, password, ip);
	}
}
