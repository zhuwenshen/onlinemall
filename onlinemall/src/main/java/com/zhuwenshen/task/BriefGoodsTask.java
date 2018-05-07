package com.zhuwenshen.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.zhuwenshen.service.BriefGoodsService;

@Component
//@EnableAsync
public class BriefGoodsTask {

	@Autowired
	private BriefGoodsService service;
	
	/**
	 * 提取goods简要信息
	 */
	//@Async
	//@Scheduled(cron = "0/10 * * * * ?") //每10秒执行一次
    public void brief() {
		try {
			service.brief();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	
	/**
	 * 全部提取
	 * @throws Exception
	 */
	//@Async
	//@Scheduled(cron = "0 0 0 * * ? ") //每天零点秒执行一次
    public void briefAll() throws Exception {
		try {
			service.briefAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
