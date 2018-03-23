package com.zhuwenshen.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhuwenshen.model.custom.JsonResult;
import com.zhuwenshen.service.UserService;

@Controller
public class RegisterController {
	private static Logger log = LoggerFactory.getLogger(RegisterController.class); 
	
	@Autowired
	private  UserService userService;

	@GetMapping("register")
	public String register() {		
		return "login/register";
	}
	
	@PostMapping("register")
	@ResponseBody
	public JsonResult register(String phone, String password) {
		log.info("注册--手机号为"+phone+",密码为"+password);
		return  userService.register(phone, password);		
	}
}
