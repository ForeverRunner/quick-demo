package com.xxd.ilin.shardingsphere.quickdemo.dao;


import java.util.concurrent.TimeUnit;

public interface RedisInnerService {

    /**
     * 设置值
     *
     * @param key
     * @param value
     * @param expire
     * @param timeUnit
     */
    void set(String key, String value, Long expire, TimeUnit timeUnit);

    /**
     * 获取值得
     *
     * @param key
     * @return
     */
    String get(String key);

    void set(String key, Object value);

    <T> T get(String key, Class<T> valueType);
}
