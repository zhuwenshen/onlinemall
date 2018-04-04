package com.zhuwenshen.service.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zhuwenshen.mapper.AdminUserMapperCustom;
import com.zhuwenshen.model.custom.JsonResult;
import com.zhuwenshen.model.custom.admin.QueryAdminUserParam;

@Service
public class AdminUserService {
	
	@Autowired
	private AdminUserMapperCustom aumc;
	
	public String queryUser(QueryAdminUserParam qaup) {
		if(qaup == null) {
			qaup = new QueryAdminUserParam();
		}
		
		// 开始分页
		Page<?> page = PageHelper.startPage(qaup.getPageNum(), qaup.getPageSize());
		aumc.selectAdminUser(qaup);
		
		return JsonResult.okToPageList(page);
	}

}
