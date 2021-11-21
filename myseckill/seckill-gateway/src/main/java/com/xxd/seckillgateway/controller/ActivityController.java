package com.xxd.seckillgateway.controller;

import com.xxd.biz.dto.Result;
import com.xxd.seckill.support.export.dto.SeckillActivityDTO;
import com.xxd.seckill.support.export.export.ActivityExportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "活动相关")
@RequestMapping("/activity")
@RestController
public class ActivityController {

    @Autowired
    private ActivityExportService activityExportService;

    @GetMapping("/getActivity")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "activityId", value = "活动id", required = true)
    )
    @ResponseBody
    public Result<SeckillActivityDTO> getActivity(@RequestParam String activityId) {
        return Result.success(activityExportService.queryActivity(activityId));
    }
}
