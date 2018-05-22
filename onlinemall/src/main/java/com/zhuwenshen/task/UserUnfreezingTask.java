package com.zhuwenshen.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.zhuwenshen.service.admin.AdminUserService;

@Component
@EnableAsync
public class UserUnfreezingTask {

	@Autowired
	private AdminUserService  service;
	
	@Async
	//@Scheduled(cron = "0 0/5 * * * ?") //每5分钟执行一次
    public void unfreezingUser() throws Exception {
		service.unfreezingUserTask();
    }
}
