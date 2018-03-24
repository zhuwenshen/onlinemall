package com.zhuwenshen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhuwenshen.model.custom.JsonResult;
import com.zhuwenshen.service.UserService;

@Controller
public class RegisterController {	
	
	@Autowired
	private  UserService userService;

	@GetMapping("register")
	public String register() {		
		return "login/register";
	}
	
	@PostMapping("register")
	@ResponseBody
	public JsonResult register(String phone, String password) {
		return  userService.register(phone, password);		
	}
}
