package com.xxd.seckillgateway.controller;

import brave.Tracer;
import com.alibaba.fastjson.JSON;
import com.xxd.seckill.support.export.dto.Result;
import com.xxd.seckill.support.export.dto.SeckillActivityDTO;
import com.xxd.seckill.support.export.export.ActivityExportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "活动相关")
@RequestMapping("/activity")
@RestController
@Slf4j
public class ActivityController {

    @Autowired
    private ActivityExportService activityExportService;

    @Autowired
    private Tracer tracer;

    @GetMapping("/getActivity")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "activityId", value = "活动id", required = true)
    )
    @ResponseBody
    public Result<SeckillActivityDTO> getActivity(@RequestParam String activityId) {
        Result<SeckillActivityDTO> seckillActivityDTOResult = activityExportService.queryActivity(activityId);
        log.info("查询活动:[{}],返回值:[{}]",activityId, JSON.toJSONString(seckillActivityDTOResult));
        return seckillActivityDTOResult;
    }
}
