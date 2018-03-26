package com.zhuwenshen.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("a")
public class AdminIndexController {
	
	@RequestMapping("/index")
	public String index(ModelAndView model) {
		model.addObject("test", "这是获取到的值");
		return "/a/index";
	}

}
