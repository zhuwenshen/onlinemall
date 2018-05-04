package com.zhuwenshen.controller.merchant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.zhuwenshen.exception.JsonResultException;
import com.zhuwenshen.model.custom.User;
import com.zhuwenshen.model.custom.merchant.QueryEvaluationParam;
import com.zhuwenshen.service.merchant.MerchantEvaluationService;

@Controller
public class MerchantEvaluationControler {

	@Autowired
	private MerchantEvaluationService service;
	
	@GetMapping("/m/evaluation/manage")
	public String manage() {
		return "/m/evaluation/manage";
	}
	
	@PostMapping("/m/evaluation/query")
	@ResponseBody
	public String query(@SessionAttribute("user")User user, QueryEvaluationParam param) {
		return service.query(user.getMerchantId(), param);
	}
	
	/**
	 * 忽略回复评价
	 * @param user
	 * @param evaluationId
	 * @return
	 */
	@PostMapping("/m/evaluation/ignore")
	@ResponseBody
	public String ignore(@SessionAttribute("user")User user, String evaluationId) {
		try {
			return service.ignore(user.getMerchantId(), evaluationId).toString();
		} catch (JsonResultException e) {
			return e.getJson().toString();
		}
	}
	
	@GetMapping("/m/evaluation/reply")
	public String replyGet() {
		return "/m/evaluation/reply";
	}
	
	/**
	 * 回复评价
	 * @param user
	 * @param evaluationId
	 * @param merchantReply
	 * @return
	 */
	@PostMapping("/m/evaluation/reply")
	@ResponseBody
	public String reply(@SessionAttribute("user")User user, String evaluationId, String merchantReply) {
		try {
			return service.reply(user.getMerchantId(), evaluationId, merchantReply).toString();
		} catch (JsonResultException e) {
			return e.getJson().toString();
		}
	}
}
