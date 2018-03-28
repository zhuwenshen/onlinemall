package com.zhuwenshen;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zhuwenshen.model.custom.QueryConstant;
import com.zhuwenshen.service.ConstantService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MysqlTest {

	/*@Autowired
	private TestService testService;
	@Autowired
	private UserService userService;*/
	
	@Autowired
	private ConstantService cs;

	//@Test
	/*public void save() {
		testService.saveTest();
	}
	
	@Test
	public void testValidPhone() {
		userService.validPhoneForRegister("123456");
	}*/
	
	@Test
	public void testPage() {
		QueryConstant qc = new QueryConstant();
		qc.setPageNum(1);
		
		System.out.println(cs.queryConstant(qc));
	}
}
