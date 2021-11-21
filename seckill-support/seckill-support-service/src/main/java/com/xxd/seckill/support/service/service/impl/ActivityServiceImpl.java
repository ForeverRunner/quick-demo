package com.xxd.seckill.support.service.service.impl;

import com.xxd.seckill.support.common.exception.BizException;
import com.xxd.seckill.support.dao.mapper.ProductInfoMapper;
import com.xxd.seckill.support.dao.mapper.SeckillActivityMapper;
import com.xxd.seckill.support.dao.model.SeckillActivity;
import com.xxd.seckill.support.service.service.ActivityService;
import com.xxd.seckill.support.service.tool.RedisTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private SeckillActivityMapper seckillActivityMapper;

    @Autowired
    private ProductInfoMapper productInfoMapper;

    @Autowired
    private RedisTool redisTool;

    public int createActivity(SeckillActivity activityInfo) throws BizException {
        SeckillActivity existRecord = seckillActivityMapper.selectByCondition(activityInfo.getProductId(),null);
        if(existRecord!=null && (existRecord.getStatus()==1 || existRecord.getStatus()==0)){
            throw new BizException("活动已存在");
        }
        activityInfo.setStatus(0);
        return seckillActivityMapper.insert(activityInfo);
    }

    public SeckillActivity queryActivityById(String productId) {
        return seckillActivityMapper.selectByProductId(productId);
    }

    public SeckillActivity queryActivityByCondition(String productId, Integer status) {
        return seckillActivityMapper.selectByCondition(productId, status);
    }

    public Integer startActivity(String productId) throws BizException {
        //查询未开始的
        SeckillActivity activityInfo = seckillActivityMapper.selectByCondition(productId, 0);
        //判断时间是否在有效期内
        Date now = new Date();
        if (now.before(activityInfo.getStartTime())) {
            throw new BizException("活动尚未开始束");
        }
        if (now.after(activityInfo.getEndTime())) {
            throw new BizException("活动已结束");
        }
        //更改活动为开始状态
        seckillActivityMapper.updateStatus(activityInfo.getId(), 1);
        //更改商品标识为秒杀
        productInfoMapper.updateTag(productId, 2);

        //将库存放入Redis
        redisTool.set("store_" + productId, String.valueOf(activityInfo.getStockNum()));
        return 1;
    }

    public Integer endActivity(String productId) throws BizException {
        //查询进行中的
        SeckillActivity activityInfo = seckillActivityMapper.selectByCondition(productId, 1);
        //更改活动为结束状态
        seckillActivityMapper.updateStatus(activityInfo.getId(), 2);
        //更改商品标识为普通
        productInfoMapper.updateTag(productId, 1);
        return 1;
    }

    public Integer queryStore(String productId) {
        //查询进行中的
        SeckillActivity activityInfo = seckillActivityMapper.selectByCondition(productId, 1);
        return activityInfo.getStockNum();
    }
}
