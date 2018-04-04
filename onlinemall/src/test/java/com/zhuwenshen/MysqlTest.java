package com.zhuwenshen;


import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zhuwenshen.service.admin.ApprovalMerchantService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MysqlTest {

	/*@Autowired
	private TestService testService;
	@Autowired
	private UserService userService;*/
	
	/*@Autowired
	private ConstantService cs;*/

	//@Test
	/*public void save() {
		testService.saveTest();
	} 
	
	@Test
	public void testValidPhone() {
		userService.validPhoneForRegister("123456");
	}*/
	
	/*@Test
	public void testPage() {
		QueryConstant qc = new QueryConstant();
		qc.setPageNum(1);
		
		System.out.println(cs.queryConstant(qc));
	}*/
	
	
/*	@Autowired
	private TestMapper testmaper;
	@Autowired
	private TestMapperCustom testMapperCustom;
	
	@org.junit.Test
	public void testInterceptor() {
		Test t = new Test();
		t.setName("123");
		t.setPassworld("15589");
		t.setSex(false);
		
		testmaper.insert(t);
		
		//List<Test> list = testmaper.select(t);
		//System.out.println(list);
		// t = testMapperCustom.selectTTTT("113", "1556");
		//System.out.println(t);
		testmaper.selectCount(t);
	}*/
	
	@Autowired
	private ApprovalMerchantService ams;
	
	@org.junit.Test
	public void testPage() {
		ams.queryApprovalMerchant(null);
	}
}
