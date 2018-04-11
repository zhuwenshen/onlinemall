package com.zhuwenshen;


import java.util.List;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zhuwenshen.mapper.MerchantWaiterMapperCustom;
import com.zhuwenshen.model.TMerchantInformation;
import com.zhuwenshen.model.TUser;
import com.zhuwenshen.model.custom.User;
import com.zhuwenshen.model.custom.admin.QueryAdminUserParam;
import com.zhuwenshen.service.ConstantService;
import com.zhuwenshen.service.admin.AdminUserService;
import com.zhuwenshen.service.admin.MerchantAminService;
import com.zhuwenshen.service.merchant.MerchantInformationService;

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
	
	//@org.junit.Test
	public void testPage2() {
		TMerchantInformation mi = new TMerchantInformation();
		mi.setNameCn("");
		mi.setNameEn("");;
		mas.queryMerchantInformation(mi, null, null);
	}
	
	@Autowired
	private AdminUserService aus;
	
	//@org.junit.Test
	public void testPage3() {
		QueryAdminUserParam qaup = new QueryAdminUserParam();
		qaup.setName("");
		aus.queryUser(qaup);
	}
	
	@Autowired
	private MerchantWaiterMapperCustom mwmc;
	
	//@org.junit.Test
	public void testWaiter() {
		List<TUser> list = mwmc.selectWaiterByWaiterId("201804081518AY120TN3TC", "20180326124492RX37ZDKP");
		System.out.println(list);
	}
	
	@Autowired
	private MerchantInformationService mis;
	@org.junit.Test
	public void testUpdateImgDes() {
		User u = new User();
		u.setMerchantId("201804022257HAMR5XDY5P");
		
			
		
		System.out.println(mis.updateImgDes(u, null));
	}
}
