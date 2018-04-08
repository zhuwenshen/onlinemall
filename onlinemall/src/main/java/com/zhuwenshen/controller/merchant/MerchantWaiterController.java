package com.zhuwenshen.controller.merchant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.zhuwenshen.model.custom.User;
import com.zhuwenshen.model.custom.merchant.QueryMerchantWaiterParam;
import com.zhuwenshen.service.merchant.MerchantWaiterService;

@Controller
public class MerchantWaiterController {
	
	@Autowired
	private MerchantWaiterService mws;

	@GetMapping("m/waiter/manage")
	public String manageHtml() {
		return "/m/waiter/manage";
	}
	
	@PostMapping("m/waiter/queryMerchantWaiter")
	@ResponseBody
	public String queryMerchantWaiter(QueryMerchantWaiterParam mwp) {
		return mws.queryMerchantWaiter(mwp);
		
	}
	
	@GetMapping("m/waiter/add")
	public String addHtml() {
		return "/m/waiter/add";
	}
	
	@PostMapping("m/waiter/add")
	@ResponseBody
	public String addMerchantWaiter(QueryMerchantWaiterParam mwp, @SessionAttribute("user") User user) {
		return mws.addMerchantWaiter( mwp, user);
	}
	
	@PostMapping("m/waiter/queryChangedrecently")
	@ResponseBody
	public String queryChangedrecently( @SessionAttribute("user") User user) {
		return mws.queryChangedrecently(user);
	}
}
