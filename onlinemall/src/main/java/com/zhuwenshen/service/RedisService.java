package com.zhuwenshen.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.zhuwenshen.exception.RedisException;
import com.zhuwenshen.model.custom.User;
import com.zhuwenshen.util.MySid;
import com.zhuwenshen.util.Redis;


/**
 * 缓存redis
 * 格式：
 * 1.对象 = token-对象名-*****（包括user对象）
 * 2.对数据库的id查询对象缓存  = id-对象名-*******
 * @author zhuwenshen
 *
 */
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
	
	
	
	/**
	 * 转换redis的的字符串
	 * @param token
	 * @return
	 */
	public static String transforToRedisToken(String token, Class<?> clazz) {
		return "token-"+clazz.getSimpleName()+"-"+token;
	}
	
	/**
	 * redis保存对象
	 * @param test
	 * @throws RedisException
	 */
	public String setObject(String token, Object o ,Class<?> clazz) throws RedisException  {
		if(o == null) {
			throw new RedisException("redis保存对象不能为空");
		}
		if(StringUtils.isEmpty(token)) {
			token = MySid.nextLong();
		}
		redis.setObject(transforToRedisToken(token, clazz), Redis.SESSION_TIME, o);
		
		return token;
	}
	
	/**
	 * redis获取对象
	 * @param token
	 * @return
	 * @throws RedisException
	 */
	public <T> T getObject(String token,Class<T> clazz) throws RedisException {
		if(StringUtils.isEmpty(token)) {
			return null;
		}
		
		T t = redis.getObjectAndActive(transforToRedisToken(token, clazz), clazz, Redis.SESSION_TIME);
		
		if(t == null) {
			throw new RedisException("redis获取对象失败");
		}
		
		return t;
	}
	
	/*public String getUserIdByToken(String token) {
		if(StringUtils.isEmpty(token)) {
			return null;
		}
		String userId = redis.get(transforTokenToRedisToken(token));
		return userId;
	}
	
	public String transforTokenToRedisToken(String token) {
		return "token-userId-"+token;
	}*/
}
