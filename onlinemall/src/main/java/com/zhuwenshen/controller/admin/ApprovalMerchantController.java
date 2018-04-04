package com.zhuwenshen.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhuwenshen.model.custom.JsonResult;
import com.zhuwenshen.model.custom.QueryApprovalMerchantParam;
import com.zhuwenshen.service.admin.ApprovalMerchantService;

@Controller
public class ApprovalMerchantController {
	
	@Autowired
	private ApprovalMerchantService approvalMerchantService;
	
	@GetMapping("a/merchant/approvalMerchant")
	public String approvalMerchant() {
		return "/a/merchant/approvalMerchant";
	}
	
	@PostMapping("a/merchant/queryApprovalMerchant")
	@ResponseBody
	public String queryApprovalMerchant(QueryApprovalMerchantParam qamp) {
		return approvalMerchantService.queryApprovalMerchant(qamp);
	}
	
	@PostMapping("a/merchant/onApproval")
	@ResponseBody
	public JsonResult onApproval(QueryApprovalMerchantParam qamp) {
		return approvalMerchantService.onApproval(qamp);
	}

}
