package com.zhuwenshen.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.zhuwenshen.exception.RedisException;
import com.zhuwenshen.model.custom.RedisKey;
import com.zhuwenshen.model.custom.RedisKind;
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
	 * @param user
	 * @param token
	 * @return
	 * @throws RedisException
	 */
	public String setSession(User user, String token) throws RedisException  {
		if(user == null) {
			throw new RedisException("redis保存对象不能为空");
		}
		if(StringUtils.isEmpty(token)) {
			token = MySid.nextLong();
		}
		setObject(token, user, User.class, Redis.SESSION_TIME ,RedisKind.SESSION);
		
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
		User user = getObject(token, User.class, Redis.SESSION_TIME ,RedisKind.SESSION);
		
		if(user == null) {
			throw new RedisException("redis获取session对象失败");
		}
		
		return user;
	}
	
	/**
	 * 删除一个session
	 * @param token
	 */
	public void deleteSession(String token) {
		if(StringUtils.isEmpty(token)) {
			return;
		}		
		
		RedisKey key = RedisKey.key(User.class, token, Redis.SESSION_TIME, RedisKind.SESSION);
		redis.delete(key);		
		
	}
		
	
	
	/**
	 * redis保存对象
	 * @param token
	 * @param o
	 * @param clazz
	 * @param kind
	 * @return
	 * @throws RedisException
	 */
	public RedisKey setObjectForever(String token, Object o ,Class<?> clazz , RedisKind kind) throws RedisException  {
		if(o == null) {
			throw new RedisException("redis保存对象不能为空");
		}
		if(StringUtils.isEmpty(token)) {
			token = MySid.nextLong();
		}
		RedisKey key  = RedisKey.key(clazz, token , kind);
		redis.setObject(key, o);
		
		return key;
	}
	
	/**
	 * redis保存对象
	 * @param token
	 * @param data
	 * @param clazz
	 * @param time
	 * @param kind
	 * @return
	 * @throws RedisException
	 */
	public RedisKey setObject(String token, Object data ,Class<?> clazz , int time , RedisKind kind) throws RedisException  {
		if(data == null) {
			throw new RedisException("redis保存对象不能为空");
		}
		if(StringUtils.isEmpty(token)) {
			token = MySid.nextLong();
		}
		RedisKey key  = RedisKey.key(clazz, token, time, kind);
		redis.setObject(key, data);
		
		return key;
	}
	
	/**
	 * 短时间保存对象
	 * @param token
	 * @param data
	 * @param clazz
	 * @param kind
	 * @return
	 * @throws RedisException
	 */
	public RedisKey setObjectShort(String token, Object data ,Class<?> clazz , RedisKind kind) throws RedisException  {		
		return setObject(token, data, clazz, Redis.OBJECT_TIME , kind);
	}
	
	/**
	 * 长时间保存对象
	 * @param token
	 * @param data
	 * @param clazz
	 * @param kind
	 * @return
	 * @throws RedisException
	 */
	public RedisKey setObjectLong(String token, Object data ,Class<?> clazz , RedisKind kind) throws RedisException  {		
		return setObject(token, data, clazz, Redis.SESSION_TIME , kind);
	}
	
	
	/**
	 * redis获取永久对象
	 * @param token
	 * @param clazz
	 * @param kind
	 * @return
	 * @throws RedisException
	 */
	public <T> T getObjectForever(String token,Class<T> clazz , RedisKind kind) throws RedisException {
		if(StringUtils.isEmpty(token)) {
			return null;
		}
		
		@SuppressWarnings("unchecked")
		T t = (T) redis.getObject(RedisKey.key(clazz, token , kind));
		
		if(t == null) {
			throw new RedisException("redis获取对象失败");
		}
		
		return t;
	}
	
	/**
	 * redis获取一段时间的对象
	 * @param token
	 * @param clazz
	 * @param time
	 * @param kind
	 * @return
	 * @throws RedisException
	 */
	public <T> T getObject(String token,Class<T> clazz,int time , RedisKind kind) throws RedisException {
		if(StringUtils.isEmpty(token)) {
			return null;
		}
		
		@SuppressWarnings("unchecked")
		T t = (T) redis.getObjectAndActive(RedisKey.key(clazz, token,time , kind));
		
		if(t == null) {
			throw new RedisException("redis获取对象失败");
		}
		
		return t;
	}
	
	/**
	 * 取出短时间对象
	 * @param token
	 * @param clazz
	 * @param kind
	 * @return
	 * @throws RedisException
	 */
	public <T> T getObjectShort(String token ,Class<T> clazz , RedisKind kind) throws RedisException  {		
		return getObject(token, clazz, Redis.OBJECT_TIME, kind);
	}
	
	/**
	 * 取出长时间对象
	 * @param token
	 * @param clazz
	 * @param kind
	 * @return
	 * @throws RedisException
	 */
	public  <T> T getObjectLong(String token ,Class<T> clazz , RedisKind kind) throws RedisException  {		
		return getObject(token,  clazz, Redis.SESSION_TIME, kind);
	}
	
	/**
	 * redis获取永久List
	 * @param token
	 * @param clazz
	 * @param kind
	 * @return
	 * @throws RedisException
	 */
	public <T> List<T> getListForever(String token,Class<T> clazz , RedisKind kind) throws RedisException {
		if(StringUtils.isEmpty(token)) {
			return null;
		}
				
		List<T> list =  redis.getList(RedisKey.key(clazz, token, kind) ,clazz);
		
		if(list == null) {
			throw new RedisException("redis获取对象失败");
		}
		
		return list;
	}

	
	
	
}
