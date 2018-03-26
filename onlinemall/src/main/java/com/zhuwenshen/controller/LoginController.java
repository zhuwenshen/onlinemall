package com.zhuwenshen.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhuwenshen.model.custom.JsonResult;
import com.zhuwenshen.service.UserService;

@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;
	

	@GetMapping("/login")
	public String login() {
		return "login/login";
	}
	
	
	@PostMapping("/login")
	@ResponseBody
	public JsonResult login(HttpSession session, String login_id, String password,String client_type, String ip, String location) {
		
		return userService.login(session, login_id, password, client_type, ip, location );
	}
}
