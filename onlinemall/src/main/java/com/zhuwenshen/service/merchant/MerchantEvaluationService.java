package com.zhuwenshen.service.merchant;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zhuwenshen.exception.JsonResultException;
import com.zhuwenshen.mapper.MerchantEvaluationMapperCustom;
import com.zhuwenshen.mapper.TEvaluationMapper;
import com.zhuwenshen.mapper.TOrderInformationMapper;
import com.zhuwenshen.mapper.TOrderMapper;
import com.zhuwenshen.model.TEvaluation;
import com.zhuwenshen.model.TOrder;
import com.zhuwenshen.model.TOrderInformation;
import com.zhuwenshen.model.custom.JsonResult;
import com.zhuwenshen.model.custom.merchant.QueryEvaluationParam;

@Service
public class MerchantEvaluationService {

	@Autowired
	private MerchantEvaluationMapperCustom evaluaTionMC;
	
	@Autowired
	private TEvaluationMapper evaluationMapper;
	
	@Autowired
	private TOrderInformationMapper orderInformationMapper;
	
	@Autowired
	private TOrderMapper orderMaper;

	public String query(String merchantId, QueryEvaluationParam param) {
		if(param == null) {
			param = new QueryEvaluationParam();
		}
		
		param.setMonthStartTime(new Date(System.currentTimeMillis()-(long)30*24*60*60*1000));
		
		// 开始分页
		Page<?> page = PageHelper.startPage(param.getPageNum(), param.getPageSize());
		evaluaTionMC.selectEvaluation(param);
		
		return JsonResult.okToPageList(page);
	}

	/**
	 * 忽略回复评价
	 * @param merchantId
	 * @param evaluationId
	 * @return
	 * @throws JsonResultException
	 */
	@Transactional
	public JsonResult ignore(String merchantId, String evaluationId) throws JsonResultException {		
		//检查参数
		checkEvaluationIsMerchant(merchantId, evaluationId);		
		
		TEvaluation ev = new TEvaluation();
		ev.setId(evaluationId);
		ev.setMerchantDeleted(true);		
		
		evaluationMapper.updateByPrimaryKeySelective(ev);
		
		return JsonResult.ok("已经忽略该评价");
	}

	private void checkEvaluationIsMerchant(String merchantId, String evaluationId) throws JsonResultException {
		
		if(StringUtils.isEmpty(merchantId)) {
			throw new JsonResultException(JsonResult.fail("merchantId不能为空"));
		}
		if(StringUtils.isEmpty(evaluationId)) {
			throw new JsonResultException(JsonResult.fail("请选择忽略的评价"));
		}
		
		TEvaluation ev = evaluationMapper.selectByPrimaryKey(evaluationId);
		if(ev == null || ev.getDeleted()) {
			throw new JsonResultException(JsonResult.fail("该评价不存在"));
		}
		
		TOrderInformation infor = orderInformationMapper.selectByPrimaryKey(ev.getOrderInforId());
		if(infor == null) {
			throw new JsonResultException(JsonResult.fail("该评价不存在"));
		}
		
		TOrder order = orderMaper.selectByPrimaryKey(infor.getOrderId());
		if(order == null || !merchantId.equals(order.getMerchantId())) {
			throw new JsonResultException(JsonResult.fail("该评价不存在"));
		}
	}

	/**
	 * 回复评价
	 * @param merchantId
	 * @param evaluationId
	 * @param merchantReply
	 * @return
	 * @throws JsonResultException
	 */
	@Transactional
	public JsonResult reply(String merchantId, String evaluationId, String merchantReply) throws JsonResultException {
		if(StringUtils.isEmpty(merchantReply)) {
			throw new JsonResultException(JsonResult.fail("回复不能为空"));
		}
		//检查参数
		checkEvaluationIsMerchant(merchantId, evaluationId);		
		
		TEvaluation ev = new TEvaluation();
		ev.setId(evaluationId);
		ev.setMerchantReply(merchantReply);
		ev.setReplyTime(new Date());
		
		evaluationMapper.updateByPrimaryKeySelective(ev);
		
		//TODO 推送给买家回复
		
		return JsonResult.ok("回复评价成功");
	}
	
	
}
