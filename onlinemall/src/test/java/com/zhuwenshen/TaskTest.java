package com.zhuwenshen;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zhuwenshen.service.BriefGoodsService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskTest {

	@Autowired
	BriefGoodsService briefGoodsService;
	
	@Test
	public void test() throws InterruptedException {
		briefGoodsService.briefAll();
	}
}
