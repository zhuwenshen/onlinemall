package com.zhuwenshen.service.merchant;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zhuwenshen.mapper.MerchantWaiterMapperCustom;
import com.zhuwenshen.model.custom.JsonResult;
import com.zhuwenshen.model.custom.User;
import com.zhuwenshen.model.custom.merchant.QueryMerchantWaiterParam;

@Service
public class MerchantWaiterService {

	@Autowired
	private MerchantWaiterMapperCustom mwmc;

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
	public String addMerchantWaiter(QueryMerchantWaiterParam mwp, User user) {
		//检查参数是否合法
		
		//检查name是否存在
		
		//检查loginId是否存在
		
		//检查当前商家是否能添加店员
		
		//在user表中插入一条数据
		//TODO 密码加密
		
		//在waiter表中插入一条数据
		
		return null;
	}

	public String queryChangedrecently(User user) {
		// 开始分页
		PageHelper.startPage(1, 10);
		List<?> list = mwmc.selectChangedrecently(user.getMerchantId());
		
		return JsonResult.okToPageList((Page<?>) list);
	}

}
