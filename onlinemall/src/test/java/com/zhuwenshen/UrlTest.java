package com.zhuwenshen;


import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zhuwenshen.model.TestUser;
import com.zhuwenshen.model.custom.JsonResult;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UrlTest {	
	
	
	@org.junit.Test
	public void testWaiter() {
		TestUser user = new TestUser();
		user.setUrl("/img/4981/5671/201804111125848A7ARTXP.png");
		
		System.out.println(JsonResult.ok("", user).toString());
	}
}
