package com.zhuwenshen;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zhuwenshen.util.MySid;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SidTest {	
	
	MySid sid;

	@Test
	public void contextLoads() {
		//System.out.println(MySid.next());
		//System.out.println(MySid.nextShort());
		System.out.println(MySid.nextLong());
	}
}
