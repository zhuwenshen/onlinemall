package com.zhuwenshen.aop;

import java.util.Properties;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zhuwenshen.service.RedisService;

@Component
@Intercepts(@Signature(method = "rollback", type = Executor.class, args = { boolean.class}))
public class RedisRollbackCachInterceptor implements Interceptor {
	
	private Logger log = LoggerFactory.getLogger(RedisRollbackCachInterceptor.class);
	@Autowired
	private RedisService redisService;

	@Override
	public Object intercept(Invocation invocation) throws Throwable {	
			
		//在这里清理redis缓存
		redisService.flushCach();
		log.debug("因为回滚，清理了redis缓存");
		Object result = invocation.proceed();		
		return result;
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);// 使用Plugin的wrap方法生成代理对象
	}

	@Override
	public void setProperties(Properties properties) {
		System.out.println(properties);

	}

}