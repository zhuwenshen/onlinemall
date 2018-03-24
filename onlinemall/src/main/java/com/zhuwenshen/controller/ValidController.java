package com.zhuwenshen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhuwenshen.service.UserService;
import com.zhuwenshen.util.ValidResultUtil;

@Controller
public class ValidController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("register/valid/phone")
	@ResponseBody
	public String validPhoneForRegister(String phone) {
		
		if(StringUtils.isEmpty(phone)) {
			return ValidResultUtil.failed();
		}
		
		return userService.validPhoneForRegister(phone);
	}
	
	@PostMapping("login/valid/login_id")
	@ResponseBody
	public String validLoginIdForLogin(String login_id) {
		
		if(StringUtils.isEmpty(login_id)) {
			return ValidResultUtil.failed();
		}
		
		return userService.validLoginIdForLogin(login_id);
	}
}
