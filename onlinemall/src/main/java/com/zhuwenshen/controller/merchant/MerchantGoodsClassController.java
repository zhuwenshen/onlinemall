package com.zhuwenshen.controller.merchant;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.zhuwenshen.exception.JsonResultException;
import com.zhuwenshen.model.custom.GoodsClass;
import com.zhuwenshen.model.custom.JsonResult;
import com.zhuwenshen.model.custom.User;
import com.zhuwenshen.service.merchant.MerchantGoodsClassService;

@Controller
public class MerchantGoodsClassController {
	
	@Autowired
	private MerchantGoodsClassService mgcs;
	
	@GetMapping("m/class/manage")
	public ModelAndView manage(ModelAndView model, String id) {
		model.setViewName("/m/goods/classManage");
		
		String goodsName = mgcs.getGoodsNameById(id);
		
		model.addObject("goodsId", id);
		model.addObject("goodsName", goodsName);
		return model;
	}
	
	@GetMapping("m/class/add")
	public ModelAndView add(ModelAndView model) {
		model.setViewName("/m/goods/classAdd");
		return model;
	}
	
	@PostMapping("m/class/update")
	@ResponseBody
	public JsonResult update(@SessionAttribute("user")User user, String data) {
		
		List<GoodsClass> list = null;
		try {
			list = JSON.parseArray(data, GoodsClass.class);
		} catch (Exception e) {
			return JsonResult.fail("参数格式错误");
		}
		System.out.println(list);
		
		try {
			return mgcs.updateClass(user.getMerchantId(), list);
		} catch (JsonResultException e) {			
			return e.getJson();
		}
	}
	
	@GetMapping("m/class/queryClass")
	@ResponseBody
	public String queryClass(String goodsId) {		
		try {
			return mgcs.queryClassByGoodsId(goodsId);
		} catch (JsonResultException e) {			
			return e.getJson().toString();
		}
	}

}
