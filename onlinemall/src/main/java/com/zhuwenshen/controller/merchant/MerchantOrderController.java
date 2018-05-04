package com.zhuwenshen.controller.merchant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.zhuwenshen.exception.JsonResultException;
import com.zhuwenshen.model.custom.User;
import com.zhuwenshen.model.custom.merchant.QueryOrderParam;
import com.zhuwenshen.service.merchant.MerchantOrderService;

@Controller
public class MerchantOrderController {

	@Autowired
	private MerchantOrderService service;
	
	@GetMapping("/m/order/manage")
	public String manage() {
		return "/m/order/manage";
	}
	
	@PostMapping("m/order/query")
	@ResponseBody
	public String query(@SessionAttribute("user")User user , QueryOrderParam param) {
		return service.query(user.getMerchantId(), param);
	}
	
	@PostMapping("m/order/getOrderInfor")
	@ResponseBody
	public String getOrderInfor(@SessionAttribute("user")User user , QueryOrderParam param) {
		try {
			return service.getOrderInfor(user.getMerchantId(), param).toString();
		} catch (JsonResultException e) {
			return e.getJson().toString();
		}
	}
	
	@PostMapping("m/order/confirmOrder")
	@ResponseBody
	public String confirmOrder(@SessionAttribute("user")User user , String orderId) {
		try {
			return service.confirmOrder(user.getMerchantId(), orderId).toString();
		} catch (JsonResultException e) {
			return e.getJson().toString();
		}
	}
		
	@GetMapping("/m/order/cancel")
	public String cancel() {
		return "/m/order/cancel";
	}
	
	/**
	 * 取消订单
	 * @param user
	 * @param orderId
	 * @param reason
	 * @return
	 */
	@PostMapping("m/order/cancelOrder")
	@ResponseBody
	public String cancelOrder(@SessionAttribute("user")User user , String orderId, String reason) {
		try {
			return service.cancelOrder(user.getMerchantId(), orderId , reason).toString();
		} catch (JsonResultException e) {
			return e.getJson().toString();
		}
	}
	
	@GetMapping("/m/order/deliver")
	public String deliver() {
		return "/m/order/deliver";
	}
	
	/**
	 * 发货
	 * @param user
	 * @param orderId
	 * @param expressNumber
	 * @param expressCompany
	 * @return
	 */
	@PostMapping("m/order/deliverOrder")
	@ResponseBody
	public String deliverOrder(@SessionAttribute("user")User user , String orderId, String expressNumber, String expressCompany) {
		try {
			return service.deliverOrder(user.getMerchantId(), orderId , expressNumber, expressCompany).toString();
		} catch (JsonResultException e) {
			return e.getJson().toString();
		}
	}
}
