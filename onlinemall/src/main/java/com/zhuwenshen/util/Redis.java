package com.zhuwenshen.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Component
public class Redis {
	
	public static int SESSION_TIME = 1*60*60;//session存活时间

	@Autowired
	private JedisPool jedisPool;

	public Jedis getResource() {
		return jedisPool.getResource();
	}

	/**
	 * 获取key的value值
	 * 
	 * @param key
	 * @return
	 */
	public String get(String key) {
		Jedis jedis = jedisPool.getResource();
		String str = "";
		try {
			str = jedis.get(key);
		} finally {
			try {
				jedis.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return str;
	}

	/**
	 * 保存一个值
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public String set(String key, String value) {
		Jedis jedis = jedisPool.getResource();
		String str = "";
		try {
			str = jedis.set(key, value);
		} finally {
			try {
				jedis.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return str;
	}

	/**
	 * 实现命令：SET key value EX seconds，设置key-value和超时时间（秒）
	 * 
	 * @param key
	 * @param value
	 * @param timeout
	 *            （以秒为单位）
	 */
	public String set(String key, int seconds, String value) {
		Jedis jedis = jedisPool.getResource();
		String str = "";
		try {
			str = jedis.setex(key, seconds, value);
		} finally {
			try {
				jedis.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return str;
	}	

	/**
	 * 实现命令：expire 设置过期时间，单位秒
	 * 
	 * @param key
	 * @return
	 */
	public void expire(String key, int seconds) {
		Jedis jedis = jedisPool.getResource();
		try {
			jedis.expire(key, seconds);
		} finally {
			try {
				jedis.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 保存一个对象
	 * @param key
	 * @param data
	 */
	public void setObject(String key, Object data) {
		set(key, JsonUtils.objectToJson(data));		
	}
	
	/**
	 * 保存一个对象一段时间
	 * @param key
	 * @param seconds
	 * @param data
	 */
	public void setObject(String key, int seconds,Object data) {
		set(key, seconds, JsonUtils.objectToJson(data));
	}
	
	/**
	 * 获取一个对象
	 * @param <T>
	 * @param key
	 * @param clazz
	 * @return 
	 * @return 
	 */
	public<T> T getObject(String key , Class<T> clazz) {
		String data = get(key);
		if(!StringUtils.isEmpty(data)) {
			 return JsonUtils.jsonToPojo(data, clazz);
		}
		return null;
	}
	
	/**
	 * 获取一个对象，并且让它继续存活一段时间
	 * @param key
	 * @param clazz
	 * @return
	 */
	public<T> T getObjectAndActive(String key , Class<T> clazz, int seconds) {		
		Jedis jedis = jedisPool.getResource();
		String data = "";
		T t = null;
		try {
			data = jedis.get(key);
			jedis.expire(key, seconds);
			if(!StringUtils.isEmpty(data)) {
				 t =  JsonUtils.jsonToPojo(data, clazz);
			}
		} finally {
			try {
				jedis.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return t;
	}
	
}
