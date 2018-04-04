package com.zhuwenshen.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhuwenshen.model.custom.admin.QueryAdminUserParam;
import com.zhuwenshen.service.admin.AdminUserService;

@Controller
public class AdminUserController {
	
	@Autowired
	private AdminUserService adminUserService;
	
	@GetMapping("a/user/manage")
	public String manage() {
		return "/a/user/manage";
	}
	
	@PostMapping("a/user/queryUser")
	@ResponseBody
	public String queryUser(QueryAdminUserParam qaup) {
		return adminUserService.queryUser(qaup);
	}

}
