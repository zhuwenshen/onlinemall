package com.zhuwenshen;


import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zhuwenshen.model.TMerchantInformation;
import com.zhuwenshen.service.ConstantService;
import com.zhuwenshen.service.admin.MerchantAminService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceTest {
	
	@Autowired
	private ConstantService cs;
	
	//@org.junit.Test
	public void testPage() {
		cs.kindConstant("system");
	}
	
	@Autowired
	private MerchantAminService mas;
	
	@org.junit.Test
	public void testPage2() {
		TMerchantInformation mi = new TMerchantInformation();
		mi.setNameCn("");
		mi.setNameEn("");;
		mas.queryMerchantInformation(mi, null, null);
	}
}
