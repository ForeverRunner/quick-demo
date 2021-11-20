package com.xxd.seckillgateway.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public abstract class AbstractGuavaCache<K, V> {
    /**
     * guava cache
     */
    private LoadingCache<K, V> cache;

    /**
     * 缓存中key的容量
     */
    private int maximumSize;

    private int expireAfterDuration;

    private TimeUnit timeUnit = TimeUnit.SECONDS;

    protected void init() {
        if (cache == null) {
            cache = CacheBuilder.newBuilder()
                    .maximumSize(maximumSize)
                    .expireAfterAccess(expireAfterDuration, timeUnit)
                    .build(new CacheLoader<K, V>() {
                        @Override
                        public V load(K key) throws Exception {
                            return fetchData(key);
                        }
                    });
        }
    }

    protected abstract V fetchData(K key);

    protected V getValue(K key) throws ExecutionException {
        return cache.get(key);
    }

    public int getMaximumSize() {
        return maximumSize;
    }

    public void setMaximumSize(int maximumSize) {
        this.maximumSize = maximumSize;
    }

    public int getExpireAfterDuration() {
        return expireAfterDuration;
    }

    public void setExpireAfterDuration(int expireAfterDuration) {
        this.expireAfterDuration = expireAfterDuration;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
    }
}
