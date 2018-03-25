package com.zhuwenshen.controller.merchant;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("m")
public class MerchantIndexController {

	@GetMapping("/index")
	public String index() {
		System.out.println("商家主页");
		return "m/index";
	}
	
	@GetMapping("memu")
	public String menu() {
		return "m/menu";
	}
}
