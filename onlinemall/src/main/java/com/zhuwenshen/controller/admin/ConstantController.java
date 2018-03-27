package com.zhuwenshen.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("a")
public class ConstantController {

	@GetMapping("/addConstant")
	public String addConstant() {
		return "/a/addConstant";
	}
}
