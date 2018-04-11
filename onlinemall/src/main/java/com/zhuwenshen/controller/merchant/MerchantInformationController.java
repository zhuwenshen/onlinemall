package com.zhuwenshen.controller.merchant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.zhuwenshen.model.custom.JsonResult;
import com.zhuwenshen.model.custom.User;
import com.zhuwenshen.model.custom.admin.QueryMerchantInformation;
import com.zhuwenshen.service.merchant.MerchantInformationService;

@Controller
public class MerchantInformationController {

	@Autowired
	private MerchantInformationService mis;
	
	@GetMapping("m/information/manage")
	public String information() {
		return "/m/information/manage";
	}
	
	@GetMapping("m/information/showMerchantInformation")
	@ResponseBody
	public String showMerchantInformation(@SessionAttribute("user") User user) {
		return mis.showMerchantInformation(user);
	}
	
	
	@PostMapping("m/information/updateMerchantPortrait")
	@ResponseBody
	public JsonResult updateMerchantPortrait(@SessionAttribute("user") User user, String imgurl) {
		return mis.updateMerchantPortrait(user, imgurl);
	}
	
	@PostMapping("m/information/updateMerchantImformation")
	@ResponseBody
	public JsonResult updateMerchantImformation(@SessionAttribute("user") User user, QueryMerchantInformation qmi) {
		return mis.updateMerchantImformation(user, qmi);
	}
	
	@PostMapping("m/information/updateImgDes")
	@ResponseBody
	public JsonResult updateImgDes(@SessionAttribute("user") User user, String[] imgDes) {
		return mis.updateImgDes(user, imgDes);
	}
}
