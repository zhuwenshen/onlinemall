package com.zhuwenshen.conf;

import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.ibatis.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.zhuwenshen.util.ContextUtils;

public class RedisCache implements Cache {
    private static final Logger log = LoggerFactory.getLogger(RedisCache.class);
    // 读写锁
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock(true);
    private final String id; // cache instance id
    private RedisTemplate<String, Object> redisTemplate;
    private static final long EXPIRE_TIME_IN_MINUTES = 30; // redis过期时间
    
    public RedisCache(String id) {
        if (id == null) {
            throw new IllegalArgumentException("Cache instances require an ID");
        }
        log.info("Redis Cache id " + id);
        this.id = id;
    }
    @Override
    public String getId() {
        return id;
    }
    /**
     * Put query result to redis
     *
     * @param key
     * @param value
     */
    @Override
    public void putObject(Object key, Object value) {
    	setRedisTemplate();
        if (value != null) {
            // 向Redis中添加数据，有效时间是30分钟
            redisTemplate.opsForValue().set(key.toString(), value, EXPIRE_TIME_IN_MINUTES, TimeUnit.MINUTES);
            System.out.println("redis缓存");
        }
    }

    @Override
    public Object getObject(Object key) {
    	setRedisTemplate();
        try {
            if (key != null) {
                Object obj = redisTemplate.opsForValue().get(key.toString());
                System.out.println("redis获取缓存");
                return obj;
            }
        } catch (Exception e) {
            log.error("redis ");
        }
        return null;
    }

    @Override
    public Object removeObject(Object key) {
    	setRedisTemplate();
        try {
            if (key != null) {
                redisTemplate.delete(key.toString());
            }
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    public void clear() {
        log.debug("清空缓存");
        try {
            Set<String> keys = redisTemplate.keys("*:" + this.id + "*");
            if (!CollectionUtils.isEmpty(keys)) {
                redisTemplate.delete(keys);
            }
        } catch (Exception e) {
        }
    }

    @Override
    public int getSize() {
        Long size = (Long) redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.dbSize();
            }
			
        });
        return size.intValue();
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return this.readWriteLock;
    }
    
    public void setRedisTemplate() {
    	if(redisTemplate == null) {
    		redisTemplate = ContextUtils.getBean("redisTemplate");
    	}    	
    }
}