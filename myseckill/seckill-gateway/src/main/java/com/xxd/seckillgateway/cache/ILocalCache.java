package com.xxd.seckillgateway.cache;

public interface ILocalCache<K, V> {
    /**
     * 从本地缓存中获取数据
     *
     * @param key
     * @return
     */
    V get(K key);
}
