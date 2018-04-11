package com.zhuwenshen.service.merchant;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zhuwenshen.mapper.MerchantWaiterMapperCustom;
import com.zhuwenshen.mapper.TMerchantWaiterMapper;
import com.zhuwenshen.mapper.TUserMapper;
import com.zhuwenshen.model.TMerchantWaiter;
import com.zhuwenshen.model.TUser;
import com.zhuwenshen.model.custom.JsonResult;
import com.zhuwenshen.model.custom.User;
import com.zhuwenshen.model.custom.merchant.QueryMerchantWaiterParam;
import com.zhuwenshen.util.ConstantUtils;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class MerchantWaiterService {

	@Autowired
	private MerchantWaiterMapperCustom mwmc;
	
	@Autowired
	private TUserMapper userMapper;
	
	@Autowired
	private TMerchantWaiterMapper merchantWaiterMapper;

	public String queryMerchantWaiter(QueryMerchantWaiterParam mwp) {

		if (mwp == null) {
			mwp = new QueryMerchantWaiterParam();
		}

		// 开始分页
		PageHelper.startPage(mwp.getPageNum(), mwp.getPageSize());

		List<?> list = mwmc.selectMerchantWaiter(mwp);

		return JsonResult.okToPageList((Page<?>) list);
	}

	/**
	 * 添加一个店员
	 * @param mwp
	 * @param user 
	 * @return
	 */
	@Transactional
	public JsonResult addMerchantWaiter(QueryMerchantWaiterParam mwp, User user) {
		//检查参数是否合法
		if(mwp == null) {
			mwp = new QueryMerchantWaiterParam();
		}
		
		if(StringUtils.isEmpty(mwp.getName())) {
			return JsonResult.fail("昵称不能为空");
		}		
		if(StringUtils.isEmpty(mwp.getLoginId())) {
			return JsonResult.fail("登录账户不能为空");
		}			
		
		//检查loginId是否存在
		TUser tuser = new TUser();
		tuser.setLoginId(mwp.getLoginId());
		tuser.setDeleted(false);
		Integer userCount = userMapper.selectCount(tuser);
		if(userCount!=null&&userCount>0) {
			return JsonResult.fail("登录账户已存在");
		}
		
		//检查当前商家是否能添加店员
		Integer count = mwmc.selectCountMerchantWaiter(user.getMerchantId());
		Integer maxCount = 5;
		if(count!=null && count >= maxCount) {
			return JsonResult.fail("店员已经到达上限 "+maxCount+ " 个，无法再创建 ");
		}
		
		//在user表中插入一条数据		
		TUser u = new TUser();
		u.setName(mwp.getName());
		u.setLoginId(mwp.getLoginId());
		// 设置默认头像
		u.setHeadPortraitUrl(ConstantUtils.HEAD_PORTRAIT_DEFAULT_URL());
		u.setIntegral(new BigDecimal(0));
		u.setAllIntegral(new BigDecimal(0));
		u.setFund(new BigDecimal(0));
		// 设置性别为隐藏
		u.setSex(3);
		// 设置用户类型为商家服务员 
		u.setUserType(4);
		
		//TODO 密码加密
		u.setLoginPassworld("123456");
		u.setFrozen(false);
		userMapper.insertSelective(u);
		
		//在waiter表中插入一条数据
		TMerchantWaiter waiter = new TMerchantWaiter();
		waiter.setMerchantId(user.getMerchantId());
		waiter.setUserId(u.getId());
		waiter.setDeleted(false);
		waiter.setShopkeeper(false);
		
		merchantWaiterMapper.insertSelective(waiter);
		
		return JsonResult.ok("添加店员成功");
	}

	public String queryChangedrecently(User user) {
		// 开始分页
		PageHelper.startPage(1, 10);
		List<?> list = mwmc.selectChangedrecently(user.getMerchantId());
		
		return JsonResult.okToPageList((Page<?>) list);
	}

	/**
	 * 重置密码
	 * @param id
	 * @param user
	 * @return
	 */
	@Transactional
	public JsonResult resetPassword(String id, User user) {
		if(StringUtils.isEmpty(id)) {
			return JsonResult.fail("id不能为空");
		}
		
		List<TUser> list = mwmc.selectWaiterByWaiterId(id, user.getMerchantId());
		if(list==null || list.isEmpty()) {
			return JsonResult.fail("无此店员，重置密码失败");
		}
		
		TUser tuser = list.get(0);		
		
		//TODO重置密码加密
		tuser.setLoginPassworld("123456");
		
		userMapper.updateByPrimaryKeySelective(tuser);
		
		return JsonResult.ok("重置密码成功");
	}

	/**
	 * 删除店员
	 * @param id
	 * @param user
	 * @return
	 */
	@Transactional
	public JsonResult delete(String id, User user) {
		
		if(StringUtils.isEmpty(id)) {
			return JsonResult.fail("id不能为空");
		}
		
		List<TUser> list = mwmc.selectWaiterByWaiterId(id, user.getMerchantId());
		if(list==null || list.isEmpty()) {
			return JsonResult.fail("无此店员，删除失败");
		}
		
		TUser tuser = list.get(0);
		//设置删除标志
		tuser.setDeleted(true);
		
		userMapper.updateByPrimaryKeySelective(tuser);
		
		TMerchantWaiter record = new TMerchantWaiter();
		record.setDeleted(true);
		Example example = new Example(TMerchantWaiter.class);
		Criteria c = example.createCriteria();
		c.andEqualTo("merchantId", user.getMerchantId());
		c.andEqualTo("userId", id);
		
		merchantWaiterMapper.updateByExampleSelective(record, example);
		
		//TOTO 强制被删除的店员下线
		
		return JsonResult.ok("删除成功");
	}
	
	

}
