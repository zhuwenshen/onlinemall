package com.zhuwenshen;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zhuwenshen.model.TUser;
import com.zhuwenshen.util.ContextUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	@Test
	public void contextLoads() {
		TUser u = new TUser();
		Class<? extends TUser> clazz = u.getClass();
		System.out.println(clazz.getSimpleName());
		System.out.println(ContextUtils.getBean("TUserMapper"));
		System.out.println(ContextUtils.getBean("tUserMapper"));
	}

}
