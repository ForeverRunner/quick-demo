package com.xxd.seckill.service.impl;

import com.xxd.seckill.support.export.dto.Result;
import com.xxd.seckill.support.export.dto.SeckillActivityDTO;
import com.xxd.seckill.support.export.export.ActivityExportService;
import com.xxd.seckill.service.SeckillActivityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SeckillActivityServiceImpl implements SeckillActivityService {
    @Autowired
    private ActivityExportService activityExportService;

    public Result<Integer> createActivity(SeckillActivityDTO seckillActivityDTO) {
        return activityExportService.createActivity(seckillActivityDTO);
    }
}
