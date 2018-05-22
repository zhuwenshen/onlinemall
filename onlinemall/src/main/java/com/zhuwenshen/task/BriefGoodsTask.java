package com.zhuwenshen.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.zhuwenshen.service.BriefGoodsService;

@Component
@EnableAsync
public class BriefGoodsTask {

	@Autowired
	private BriefGoodsService service;
	
	/**
	 * 提取goods简要信息
	 */
	//@Scheduled(cron = "0/10 * * * * ?") //每10秒执行一次
	@Async
	//@Scheduled(cron = "0 0/6 * * * ?") //每6分钟执行一次
    public void brief() {
		try {
			service.brief(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	
	/**
	 * 全部提取goods简要信息
	 * @throws Exception
	 */
	@Async
	//@Scheduled(cron = "0 0 0 * * ? ") //每天零点秒执行一次
    public void briefAll() throws Exception {
		try {
			service.brief(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
