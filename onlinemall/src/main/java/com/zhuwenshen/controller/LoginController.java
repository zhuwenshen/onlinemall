package com.zhuwenshen.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhuwenshen.model.custom.JsonResult;
import com.zhuwenshen.service.UserService;
import com.zhuwenshen.util.IpUtils;

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
	public JsonResult login(HttpServletRequest request, String login_id, String password,String client_type, String location) {
		String ip = IpUtils.getIpAddr(request);
		return userService.login(request.getSession(), login_id, password, client_type, ip, location );
	}
	
	@PostMapping("/logout")
	@ResponseBody
	public String logout(HttpServletResponse response,HttpServletRequest request ) {	
		String ip = IpUtils.getIpAddr(request);
		return userService.logout(response , ip).toString();
	}
	
	@GetMapping("/logout")
	public String logoutGet(HttpServletResponse response,HttpServletRequest request ) {	
		String ip = IpUtils.getIpAddr(request);
		userService.logout(response , ip);
		return "login/login";
	}
}
