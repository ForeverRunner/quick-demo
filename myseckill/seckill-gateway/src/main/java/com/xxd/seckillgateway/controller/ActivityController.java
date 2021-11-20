package com.xxd.seckillgateway.controller;

import com.xxd.biz.dto.Result;
import com.xxd.seckillgateway.cache.impl.ActivityLocalCache;
import com.xxd.seckillgateway.dto.SeckillActivityDto;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/activity")
@RestController
public class ActivityController {

    @Autowired
    private ActivityLocalCache activityLocalCache;

    @GetMapping("/getActivity")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "activityId", value = "活动id", required = true)
    )
    @ResponseBody
    public Result<SeckillActivityDto> getActivity(@RequestParam String activityId) {
        return Result.success(activityLocalCache.get(activityId));
    }
}
