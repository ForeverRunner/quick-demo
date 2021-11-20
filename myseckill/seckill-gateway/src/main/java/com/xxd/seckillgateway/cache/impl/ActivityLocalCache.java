package com.xxd.seckillgateway.cache.impl;

import com.xxd.seckillgateway.cache.AbstractGuavaCache;
import com.xxd.seckillgateway.cache.ILocalCache;
import com.xxd.seckillgateway.dto.SeckillActivityDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.concurrent.ExecutionException;

@Slf4j
@Service("activityLocalCache")
public class ActivityLocalCache extends AbstractGuavaCache<String, SeckillActivityDto> implements ILocalCache<String, SeckillActivityDto> {
    @Value("${seckill.cache.activity.maximumsize:10}")
    private int activityCacheMaximumSize;

    @Value("${seckill.cache.activity.expire:1}")
    private int activityCacheExpire;

    @PostConstruct
    @Override
    protected void init() {
        setMaximumSize(activityCacheMaximumSize);
        setExpireAfterDuration(activityCacheExpire);
        super.init();
    }

    protected SeckillActivityDto fetchData(String key) {
        SeckillActivityDto seckillActivityDto = new SeckillActivityDto();
        seckillActivityDto.setActivityId(key);
        seckillActivityDto.setStartTime(new Date());
        seckillActivityDto.setEndTime(new Date());
        return seckillActivityDto;
    }

    public SeckillActivityDto get(String key) {
        try {
            return getValue(key);
        } catch (ExecutionException e) {
            log.error("activityLocalCache key:[{}],exception:[{}]", key, e);
            return null;
        }
    }
}
