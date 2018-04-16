package com.zhuwenshen.aop;

import java.util.Properties;

import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.zhuwenshen.service.RedisService;
import com.zhuwenshen.util.SqlUtils;

@Component
@Intercepts({
		@Signature(method = "query", type = Executor.class, args = { MappedStatement.class, Object.class,
				RowBounds.class, ResultHandler.class }),
		@Signature(method = "query", type = Executor.class, args = { MappedStatement.class, Object.class,
				RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class }) })
public class RedisQueryCachInterceptor implements Interceptor {

	private Logger log = LoggerFactory.getLogger(RedisQueryCachInterceptor.class);
	@Autowired
	private RedisService redisService;

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		MappedStatement ms = (MappedStatement) invocation.getArgs()[0];
		BoundSql boundSql = ms.getBoundSql(invocation.getArgs()[1]);
		String key = SqlUtils.getRedisCachKey(boundSql);

		// System.out.println("key:"+key);
		// System.out.println("返回类型："+ms.getResultMaps().get(0).getType());
		// System.out.println("*********************");

		// 通过redis获取缓存 (返回值必定是list)
		//当返回值只有一个时
		if (!StringUtils.isEmpty(key) && ms.getResultMaps().size() == 1) {
			Object o = redisService.getCachObject(key, ms.getResultMaps().get(0).getType(), true);

			if (o != null) {
				log.trace("通过redis获取值，key：" + key + ",data:" + o);
				return o;
			}
		}
		Object result = invocation.proceed();

		//加入redis缓存
		//System.out.println(result);
		if(!StringUtils.isEmpty(key)) {
			redisService.setCachObject(key, result);
			log.trace("缓存了对象，key：" + key + ",data:" + result);

		}
		
		return result;
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);// 使用Plugin的wrap方法生成代理对象
	}

	@Override
	public void setProperties(Properties properties) {

	}

}
