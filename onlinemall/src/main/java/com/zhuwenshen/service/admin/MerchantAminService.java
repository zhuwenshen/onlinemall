package com.zhuwenshen.service.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zhuwenshen.mapper.TMerchantInformationMapperCustom;
import com.zhuwenshen.model.TMerchantInformation;
import com.zhuwenshen.model.custom.JsonResult;

@Service
public class MerchantAminService {
	
	@Autowired
	private TMerchantInformationMapperCustom mimc;

	public String queryMerchantInformation(TMerchantInformation mi, Integer pageNum, Integer pageSize) {

		if (pageNum == null) {
			pageNum = 1;
		}
		if (pageSize == null) {
			pageSize = 10;
		}
		if (mi == null) {
			mi = new TMerchantInformation();
		}

		// 开始分页
		PageHelper.startPage(pageNum, pageSize);
		
		List<?> list = mimc.selectMerchantInformation(mi);		
		
		return JsonResult.okToPageList((Page<?>) list);
	}

}
