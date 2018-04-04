package com.zhuwenshen.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhuwenshen.model.TMerchantInformation;
import com.zhuwenshen.service.admin.MerchantAminService;

@Controller
public class AdminMerchantInformation {
	
	@Autowired
	private MerchantAminService merchantAminService;

	@GetMapping("a/merchant/information")
	public String information() {
		return "/a/merchant/information";
	}
	
	@PostMapping("a/merchant/queryMerchantInformation")
	@ResponseBody
	public String queryMerchantInformation(TMerchantInformation mi, Integer pageNum, Integer pageSize) {
		return merchantAminService.queryMerchantInformation(mi ,pageNum , pageSize);
	}
}
