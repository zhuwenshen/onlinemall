package com.zhuwenshen.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.zhuwenshen.exception.RedisException;
import com.zhuwenshen.model.custom.User;
import com.zhuwenshen.util.MySid;
import com.zhuwenshen.util.Redis;

@Service
public class RedisService {

	@Autowired
	private Redis redis;
	
	/**
	 * redis保存session
	 * @param test
	 * @throws RedisException
	 */
	public String setSession(User user, String token) throws RedisException  {
		if(user == null) {
			throw new RedisException("redis保存对象不能为空");
		}
		if(StringUtils.isEmpty(token)) {
			token = MySid.nextLong();
		}
		redis.setObject(token, Redis.SESSION_TIME, user);
		
		return token;
	}
	
	/**
	 * redis获取session对象
	 * @param token
	 * @return
	 * @throws RedisException
	 */
	public User getSession(String token) throws RedisException {
		if(StringUtils.isEmpty(token)) {
			return null;
		}
		
		User user = redis.getObjectAndActive(token, User.class, Redis.SESSION_TIME);
		
		if(user == null) {
			throw new RedisException("redis获取session对象失败");
		}
		
		return user;
	}
}
