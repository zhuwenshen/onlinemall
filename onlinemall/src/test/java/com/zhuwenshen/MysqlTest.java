package com.zhuwenshen;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zhuwenshen.service.TestService;
import com.zhuwenshen.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MysqlTest {

	@Autowired
	private TestService testService;
	@Autowired
	private UserService userService;

	//@Test
	public void save() {
		testService.saveTest();
	}
	
	@Test
	public void testValidPhone() {
		userService.validPhoneForRegister("123456");
	}
}
