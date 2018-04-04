package com.zhuwenshen.service.user;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.zhuwenshen.mapper.TIdentifiedInforOfMerchantMapper;
import com.zhuwenshen.mapper.TMerchantInformationMapper;
import com.zhuwenshen.mapper.TUserMapper;
import com.zhuwenshen.model.TIdentifiedInforOfMerchant;
import com.zhuwenshen.model.TMerchantInformation;
import com.zhuwenshen.model.TUser;
import com.zhuwenshen.model.custom.JsonResult;
import com.zhuwenshen.model.custom.User;
import com.zhuwenshen.service.ConstantService;
import com.zhuwenshen.util.ConstantUtils;

@Service
public class BeMerchantService {
	
	@Autowired
	private TMerchantInformationMapper mim;
	
	@Autowired
	private ConstantService cs;
	
	@Autowired
	private TIdentifiedInforOfMerchantMapper identifiedMapper;
	
	@Autowired
	private TUserMapper userMapper;
	

	/**
	 * 用户成为商家
	 * @param mi
	 * @param user 
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public JsonResult toBeMerchant(TMerchantInformation mi, User user) {
		if(user == null) {
			return JsonResult.fail("用户还没登录");
		}
		
		if(user.getUserType()!= 2) {
			switch (user.getUserType()) {
			case 1:
				return JsonResult.fail("先请注册");	
			case 3:
				return JsonResult.fail("已经是商家或者正在申请中");				
			case 4:
				return JsonResult.fail("店员无法申请成为商家");				
			case 5:
							
			case 6:
				return JsonResult.fail("店员无法申请成为商家");
			default:
				return JsonResult.fail("无法申请成为商家");
			}
		}
		
		if(StringUtils.isEmpty(mi.getNameCn())) {
			return JsonResult.fail("店名不能为空");
		}
		if(StringUtils.isEmpty(mi.getNameEn())) {
			return JsonResult.fail("店名（英文）不能为空");
		}
		if(StringUtils.isEmpty(mi.getPhone())) {
			return JsonResult.fail("联系电话不能为空");
		}
		if(StringUtils.isEmpty(mi.getDetailedAddress())) {
			return JsonResult.fail("详细地址不能为空");
		}		
		//检查店名是否重复
		TMerchantInformation record2 = new TMerchantInformation();
		record2.setNameCn(mi.getNameCn());
		int count = mim.selectCount(record2);
		if(count>0) {
			return JsonResult.fail("店名已存在");
		}
		//检查店名（英文）是否重复
		record2.setNameCn(null);
		record2.setNameEn(mi.getNameEn());
		count = mim.selectCount(record2);
		if(count>0) {
			return JsonResult.fail("店名（英文）已存在");
		}
		
		//更改用户类型为3
		TUser u = new TUser();
		u.setId(user.getId());
		u.setUserType(3);
		userMapper.updateByPrimaryKeySelective(u);
		
		mi.setAllIntegral(new BigDecimal(0));
		mi.setIntegral(new BigDecimal(0));
		mi.setFund(new BigDecimal(0));
		mi.setDeleted(false);
		
		mi.setUserId(user.getId());
		mi.setCertificationLevel(0);
		mi.setMerchantPortraitUrl(ConstantUtils.HEAD_PORTRAIT_DEFAULT_URL());
		
		Long num = Long.valueOf(ConstantUtils.MERCHANT_NUM()) +1;
		
		
		TMerchantInformation record = new TMerchantInformation();
		record.setNum(num);
		while(true) {
			if(mim.selectCount(record)==0) {
				break;
			}
			num++;
		}
		mi.setNum(num);		
		
		cs.updateConstant(ConstantUtils.SYSTEM_KIND, "merchant_num", num.toString());
		
		mim.insert(mi);		
		
		
		//在管理员审核商家表中插入相关信息
		TIdentifiedInforOfMerchant identifiedInforOfMerchant = new TIdentifiedInforOfMerchant();
		
		identifiedInforOfMerchant.setUserId(user.getId());
		identifiedInforOfMerchant.setMerchantInformationId(mi.getId());
		identifiedInforOfMerchant.setApprovalType(0);
		identifiedInforOfMerchant.setApplyTime(new Date());
		identifiedInforOfMerchant.setCertificationWorkflow(1);		
		
		identifiedMapper.insert(identifiedInforOfMerchant);
		
		//TODO 给管理员推送审核信息
		
		
		
		return JsonResult.ok("申请成功，请耐心等待管理员审核");
	}

}
