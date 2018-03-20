package com.zhuwenshen.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhuwenshen.mapper.TestMapper;
import com.zhuwenshen.model.Test;
import com.zhuwenshen.util.MySid;

@Service
public class TestService {	
	
	@Autowired
	private TestMapper testMapper;
	
	@Transactional
	public void saveTest() {
		Test test = new Test();
		test.setId(MySid.nextLong());
		test.setName("123464");
		test.setSex(false);
		test.setPassworld("dddddddd");
		
		testMapper.insert(test);
	}
}
