package com.zhuwenshen.service;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zhuwenshen.mapper.TUserMapper;
import com.zhuwenshen.mapper.TUserMapperCustom;
import com.zhuwenshen.model.TUser;
import com.zhuwenshen.model.custom.JsonResult;
import com.zhuwenshen.util.MySid;
import com.zhuwenshen.util.ValidResultUtil;

@Service
public class UserService {
	
	private static Logger log = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private TUserMapperCustom userMapperCustom;
	
	@Autowired
	private TUserMapper userMapper;
	
	/**
	 * 验证手机号是否已经注册，或者已经是登录ID
	 * @param phone
	 * @return
	 */
	public String validPhoneForRegister(String phone) {
		int num = userMapperCustom.selectCountPhoneOrLoginId(phone);
		
		if(num == 0) {
			return ValidResultUtil.ok();
		}
		
		return ValidResultUtil.failed();
	}
	
	/**
	 * 用户注册
	 * @param phone
	 * @param password
	 * @return
	 */
	 @Transactional(propagation=Propagation.REQUIRED) 
	public JsonResult register(String phone, String password) {
		 log.debug("注册开始phone="+phone+",password="+password);
		//验证手机号是否已经注册
		int num = userMapperCustom.selectCountPhoneOrLoginId(phone);
		if(num > 0) {
			return JsonResult.fail("手机号："+phone+" 已经注册！");
		}
		
		//在t_user表中增加记录
		TUser user = new TUser();
		/*user.setId(MySid.nextLong());
		
		while(userMapperCustom.selectCountById(user.getId())>0) {
			user.setId(MySid.nextLong());
		}*/
		
		
		user.setName("用户"+MySid.nextInt());
		user.setPhone(phone);
		//设置默认头像
		user.setHeadPortraitUrl("");
		user.setIntegral(new BigDecimal(0));
		user.setAllIntegral(new BigDecimal(0));
		user.setFund(new BigDecimal(0));
		//设置性别为隐藏
		user.setSex(3);
		//设置用户类型为普通用户
		user.setUserType(2);
		user.setLoginId(phone);
		user.setLoginPassworld(password);
		user.setFrozen(false);
		/*user.setDeleted(false);
		user.setCreateTime(new Date());
		user.setCreateUserid(user.getId());
		user.setUpdateTime(new Date());
		user.setUpdateUserid(user.getId());*/
		userMapper.insertSelective(user);
		log.info(user.toString());		
		log.info("注册完成");
		
		return JsonResult.ok("注册成功");
	}
	
}
