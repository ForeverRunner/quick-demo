package com.xxd.seckillgateway.service;

import com.xxd.seckill.support.export.dto.Result;
import com.xxd.seckill.support.export.dto.SeckillActivityDTO;

public interface SeckillActivityService {
    /**
     * 创建活动
     *
     * @param seckillActivityDTO
     * @return
     */
    Result<Integer> createActivity(SeckillActivityDTO seckillActivityDTO);
}
