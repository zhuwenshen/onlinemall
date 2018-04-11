package com.zhuwenshen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StaticController {

	@GetMapping("adminCss")
	public String adminCss() {
		return "adminCss";
	}
	
	@GetMapping("uploadPortrait")
	public String uploadPortrait() {
		return "uploadPortrait";
	}
	
	@GetMapping("uploadImg")
	public String uploadImg() {
		return "uploadImg";
	}
}
