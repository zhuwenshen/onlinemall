package com.zhuwenshen.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhuwenshen.model.TGlobalConstant;
import com.zhuwenshen.model.custom.JsonResult;
import com.zhuwenshen.model.custom.QueryConstant;
import com.zhuwenshen.service.ConstantService;

@Controller
public class ConstantController {
	
	@Autowired
	private ConstantService cs;

	@GetMapping("a/constant")
	public String constant() {
		return "/a/constant";
	}
	
	@GetMapping("a/addConstant")
	public String addConstant() {
		return "/a/addConstant";
	}
	
	@PostMapping("a/addConstant")
	@ResponseBody
	public JsonResult addConstant(TGlobalConstant gc) {
		System.out.println(gc);
		return cs.addConstant(gc);
	}
	/**
	 * 根据kind获取kindName
	 * @param kind
	 * @return
	 */
	@GetMapping("c/getKindNameByKind")
	@ResponseBody
	public JsonResult getKindNameByKind(String kind) {
		String s = cs.getKindNameByKind(kind);
		if(s == null) {
			return JsonResult.fail("");
		}
		return JsonResult.ok("", s);
		
	}
	
	/**
	 * 获取最近插入或更改的常量记录（三天）
	 * @return
	 */
	@GetMapping("a/getConstantChangedLately")
	@ResponseBody
	public JsonResult getConstantChangedLately() {
		return cs.getConstantChangedLately();
	}
	
	/**
	 * 分页查询常量记录
	 * @return
	 */
	@PostMapping("a/queryConstant")
	@ResponseBody
	public String queryConstant(QueryConstant qc) {
		return cs.queryConstant(qc);
	}
}
