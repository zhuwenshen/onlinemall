package com.zhuwenshen.mapper;

import java.util.List;

import com.zhuwenshen.model.custom.admin.QueryAdminUser;
import com.zhuwenshen.model.custom.admin.QueryAdminUserParam;

public interface AdminUserMapperCustom {
	
	public List<QueryAdminUser> selectAdminUser(QueryAdminUserParam qaup);

}
