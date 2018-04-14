package com.zhuwenshen.controller.merchant;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.zhuwenshen.exception.JsonResultException;
import com.zhuwenshen.model.custom.JsonResult;
import com.zhuwenshen.model.custom.User;
import com.zhuwenshen.model.custom.merchant.QueryMerchantGoodsParam;
import com.zhuwenshen.service.merchant.MerchantGoodsService;

@Controller
public class MerchantGoodsController {
	
	@Autowired
	private MerchantGoodsService mgs;
	
	@GetMapping("/m/goods/manage")
	public String manage() {
		return "/m/goods/manage";
	}
	
	@PostMapping("/m/goods/queryGoods")
	@ResponseBody
	public String queryGoods(@SessionAttribute("user") User user, QueryMerchantGoodsParam goods ) {
		System.out.println(goods);
		return mgs.queryGoods(user, goods);
	}
	
	@GetMapping("/m/goods/goodsLabel")
	@ResponseBody
	public String goodsLabel(@SessionAttribute("user") User user) {
		return mgs.goodsLabel(user);
	}
	
	@GetMapping("/m/goods/add")
	public String add() {
		return "/m/goods/add";
	}
	
	@PostMapping("/m/goods/addGoods")
	@ResponseBody
	public JsonResult addGoods( @SessionAttribute("user") User user, QueryMerchantGoodsParam goods ) {
		//System.out.println(goods);
		//return JsonResult.fail("参数调试");
		try {
			return mgs.addGoods(user, goods);
		} catch (JsonResultException e) {
			return e.getJson();
		}
	}
	
	@PostMapping("/m/goods/addGoodsLable")
	@ResponseBody
	public JsonResult addGoodsLable(@SessionAttribute("user") User user, String	 goodsId, String[] label) {
		return mgs.addGoodsLable(user, goodsId, label);
		
	}
	
	@GetMapping("m/goods/queryChangedRecently")
	@ResponseBody
	public String queryChangedRecently( @SessionAttribute("user") User user) {
		return mgs.queryChangeRrecently(user);
	}
	
	@GetMapping("m/goods/updateGoods")
	public String updateGoods() {
		return "/m/goods/update";
	}
	
	@GetMapping("m/goods/getUpdateGoods")
	@ResponseBody
	public String getUpdateGoods(@SessionAttribute("user") User user, String id) {
		return mgs.getGoodsDetailById(user, id);
	}
	
	@PostMapping("m/goods/updateGoods")
	@ResponseBody
	public JsonResult updateGoods(@SessionAttribute("user") User user,  QueryMerchantGoodsParam goods) {
		try {
			return mgs.updateGoods(user, goods);
		} catch (JsonResultException e) {			
			return e.getJson();
		}		
	}
	
	@PostMapping("m/goods/unshelve")
	@ResponseBody
	public JsonResult unshelve(@SessionAttribute("user") User user, String id) {
		return mgs.unshelveGoods(user , id);
	}
	
	@PostMapping("m/goods/updateUnshelveTime")
	@ResponseBody
	public JsonResult updateUnshelveTime(@SessionAttribute("user") User user, String id , Date unshelveTime) {
		return mgs.updateUnshelveTime(user , id , unshelveTime);
	}

	@PostMapping("m/goods/deleteGoods")
	@ResponseBody
	public JsonResult deleteGoods(@SessionAttribute("user") User user, String id) {
		return mgs.deleteGoods(user , id);
	}
}
