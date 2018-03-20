package com.zhuwenshen;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zhuwenshen.exception.RedisException;
import com.zhuwenshen.model.custom.User;
import com.zhuwenshen.service.RedisService;
import com.zhuwenshen.util.MySid;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {
	
	@Autowired
	private RedisService redisService;	
	

	@Test
	public void setAndGet() throws RedisException {
		User u = new User();
		
		u.setId(MySid.nextLong());
		u.setName("这是名字");
		u.setPassworld("这是密码");
		u.setSex(false);
		
		String  token = redisService.setSession(u, null);
		
		User user = redisService.getSession(token);
		System.out.println(u);
		System.out.println(user);
	}
}
