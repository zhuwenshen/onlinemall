package com.zhuwenshen.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.zhuwenshen.model.TMerchantInformation;
import com.zhuwenshen.model.custom.JsonResult;
import com.zhuwenshen.model.custom.User;
import com.zhuwenshen.service.user.BeMerchantService;

@Controller
public class BeMerchantController {

	@Autowired
	private BeMerchantService beMerchantService;
	
	@PostMapping("u/toBeMerchant")
	@ResponseBody
	public JsonResult toBeMerchant(TMerchantInformation mi , @SessionAttribute("user") User user) {
		return beMerchantService.toBeMerchant(mi , user);
	}
}
