package com.zhuwenshen.task;

import java.util.Date;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
//@EnableAsync
public class Task2 {

	//@Async
	//@Scheduled(cron = "0/10 * * * * ?") //每2秒执行一次
    public void doSomething() throws Exception {
		Thread.sleep(5000);
        System.out.println("1每2秒执行一个的定时任务："+new Date());
    }
}
