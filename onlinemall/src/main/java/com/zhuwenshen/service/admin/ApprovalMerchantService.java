package com.zhuwenshen.service.admin;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zhuwenshen.mapper.ApprovalMerchantMapperCustom;
import com.zhuwenshen.mapper.TIdentifiedInforOfMerchantMapper;
import com.zhuwenshen.mapper.TMerchantInformationMapper;
import com.zhuwenshen.mapper.TUserMapper;
import com.zhuwenshen.model.TIdentifiedInforOfMerchant;
import com.zhuwenshen.model.TMerchantInformation;
import com.zhuwenshen.model.TUser;
import com.zhuwenshen.model.custom.JsonResult;
import com.zhuwenshen.model.custom.QueryApprovalMerchantParam;
import com.zhuwenshen.service.ConstantService;

@Service
public class ApprovalMerchantService {
	
	@Autowired
	private ConstantService cs;
	
	@Autowired
	private ApprovalMerchantMapperCustom ammc;
	
	@Autowired
	private TIdentifiedInforOfMerchantMapper iiomm;
	
	@Autowired
	private TMerchantInformationMapper mim;
	
	@Autowired
	private TUserMapper userMapper;

	public String queryApprovalMerchant(QueryApprovalMerchantParam qamp) {
		if(qamp == null) {
			qamp = new QueryApprovalMerchantParam();
			qamp.setPageNum(1);
			qamp.setPageSize(10);
		}		
		
		// 开始分页
		Page<?> page = PageHelper.startPage(qamp.getPageNum(), qamp.getPageSize());
		ammc.selectApprovalMerchant(qamp);			
		
		return JsonResult.okToPageList(page);
	}

	/**
	 * 审核
	 * @param qamp
	 * @return
	 */
	@Transactional(rollbackFor=Exception.class,propagation = Propagation.REQUIRED)
	public JsonResult onApproval(QueryApprovalMerchantParam qamp) {
		
		//检查输入参数
		if(qamp == null) {
			return JsonResult.fail("参数错误");
		}
		if(StringUtils.isEmpty(qamp.getIdU())) {
			return JsonResult.fail("id不能为空");
		}
		if(StringUtils.isEmpty(qamp.getApprovalTypeU())) {
			return JsonResult.fail("审核结果类型不能为空");
		}
		if(StringUtils.isEmpty(qamp.getApprovalResultU())) {
			return JsonResult.fail("审核结果原因不能为空");
		}
		Integer approvalType  = null;
		try {
			approvalType = Integer.valueOf(qamp.getApprovalTypeU());
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return JsonResult.fail("审核结果类型应为整型");
		}
		
		if(approvalType.equals(0)) {
			return JsonResult.fail("审核结果类型不能为未审核");
		}
		
		String approval = cs.getNameByKindAndValue("approval_type_of_merchant", approvalType.toString());
		if(StringUtils.isEmpty(approval)) {
			return JsonResult.fail("审核结果类型值错误");
		}	
		
		Integer certificationLevel  = null;
		try {
			certificationLevel = Integer.valueOf(qamp.getCertificationLevelU());
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return JsonResult.fail("认证等级应为整型");
		}
		
		String certificationLevelU = cs.getNameByKindAndValue("certification_level_of_merchant", approvalType.toString());
		if(StringUtils.isEmpty(certificationLevelU)) {
			return JsonResult.fail("认证等级值错误");
		}
		
		if(certificationLevel.equals(0)) {
			return JsonResult.fail("认证等级不能为未审核");
		}
		
		
		//查出审核信息
		TIdentifiedInforOfMerchant iiom = iiomm.selectByPrimaryKey(qamp.getIdU());
		if(iiom == null || iiom.getDeleted()) {
			return JsonResult.fail("无审核信息");
		}
		//检查是否符合要求
		if(iiom.getApprovalType() != 0) {
			return JsonResult.fail("该商家审核信息已经审核");
		}		
		
		//更新审核信息为通过或失败
		iiom.setCertificationWorkflow(2);
		iiom.setApprovalType(approvalType);
		iiom.setApprovalResult(qamp.getApprovalResultU());
		iiom.setApprovalTime(new Date());		
		
		
		
		//更新商家认证状态
		TMerchantInformation mi = mim.selectByPrimaryKey(iiom.getMerchantInformationId());
		
		TUser user = userMapper.selectByPrimaryKey(mi.getUserId());
		if(StringUtils.isEmpty(user.getRealName())) {
			return JsonResult.fail("该商家没有实名制");
		}
		mi.setCertificationLevel(certificationLevel);
		
		iiomm.updateByPrimaryKeySelective(iiom);
		mim.updateByPrimaryKeySelective(mi);
		
		//TODO 推送给商家结果
		
		
		
		return JsonResult.ok("审核成功");
	}

}
