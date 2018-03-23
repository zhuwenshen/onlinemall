package com.zhuwenshen.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/error")
public class ErrorController {
	
	@GetMapping("/msg")
	public String errorMsg(String msg) {
		return msg;
	}

}
