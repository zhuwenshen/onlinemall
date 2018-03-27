package com.zhuwenshen.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("a")
public class AdminIndexController {
	
	@RequestMapping("/index")
	public String index() {
				return "/a/index";
	}
	
	@GetMapping("memu")
	public String menu() {
		return "/a/menu";
	}

}