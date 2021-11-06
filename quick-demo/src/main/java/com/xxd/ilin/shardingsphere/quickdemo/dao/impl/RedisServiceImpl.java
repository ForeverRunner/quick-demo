package com.xxd.ilin.shardingsphere.quickdemo.dao.impl;

import com.xxd.ilin.shardingsphere.quickdemo.dao.RedisInnerService;
import com.xxd.ilin.shardingsphere.quickdemo.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class RedisServiceImpl implements RedisInnerService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public void set(String key, String value, Long expire, TimeUnit timeUnit) {
        stringRedisTemplate.opsForValue().set(key, value, expire, timeUnit);
    }

    public String get(String key) {
        return stringRedisTemplate.opsForValue().get(key);

    }

    @Override
    public void set(String key, Object value) {
        stringRedisTemplate.opsForValue().set(key, JsonUtil.getInstance().objectToStr(value));
    }

    @Override
    public <T> T get(String key, Class<T> valueType) {
        T value = null;
        String valueStr = get(key);
        if (valueStr == null) {
            return null;
        }
        return JsonUtil.getInstance().strToObject(valueStr, valueType);
    }
}
