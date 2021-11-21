package com.xxd.seckill.support.launcher.export;

import com.xxd.seckill.support.common.exception.BizException;
import com.xxd.seckill.support.dao.model.SeckillActivity;
import com.xxd.seckill.support.export.constant.ResultCodeConstant;
import com.xxd.seckill.support.export.dto.Result;
import com.xxd.seckill.support.export.dto.SeckillActivityDTO;
import com.xxd.seckill.support.export.export.ActivityExportService;
import com.xxd.seckill.support.service.service.ActivityService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.rpc.RpcContext;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ActivityExportServiceImpl implements ActivityExportService {
    @Autowired
    private ActivityService activityService;

    public Result<Integer> queryStore(String productId) {
        try {
            Integer count = activityService.queryStore(productId);
            return new Result(count);
        } catch (Exception e) {
            log.error("发生异常了:{}", e);
        }
        return new Result(ResultCodeConstant.SYSTEM_EXCEPTION, "系统异常", null);
    }

    public Result<Integer> createActivity(SeckillActivityDTO activityDTO) {
        try {
            SeckillActivity activityInfo = new SeckillActivity();

            BeanUtils.copyProperties(activityDTO, activityInfo);

            int count = activityService.createActivity(activityInfo);

            return new Result(count);
        } catch (BizException e) {
            log.error("发生异常了:{}", e);
            return new Result(ResultCodeConstant.SYSTEM_EXCEPTION, e.getMessage(), null);
        } catch (Exception e) {
            log.error("发生异常了:{}", e);
        }
        return new Result(ResultCodeConstant.SYSTEM_EXCEPTION, "系统异常", null);

    }

    public Result<SeckillActivityDTO> queryActivity(String productId) {
        SeckillActivity activityInfo = activityService.queryActivityById(productId);
        if (activityInfo == null) {
            log.info("查询活动为空:{}", productId);
            return new Result<SeckillActivityDTO>(null);
        }
        SeckillActivityDTO activityDTO = new SeckillActivityDTO();
        BeanUtils.copyProperties(activityInfo, activityDTO);
        return new Result(activityDTO);
    }

    public Result<SeckillActivityDTO> queryActivityByCondition(String productId, Integer status) {
        SeckillActivity activityInfo = activityService.queryActivityByCondition(productId, status);
        if (activityInfo == null) {
            return new Result(null);
        }

        SeckillActivityDTO activityDTO = new SeckillActivityDTO();

        BeanUtils.copyProperties(activityInfo, activityDTO);

        return new Result(activityDTO);
    }

    public Result<Integer> startActivity(String productId) {
        Integer count = 0;
        try {
            count = activityService.startActivity(productId);
        } catch (BizException e) {
            return new Result(ResultCodeConstant.SYSTEM_EXCEPTION, e.getErrorCode(), count);
        } catch (Exception e) {
            return new Result(ResultCodeConstant.SYSTEM_EXCEPTION, "系统异常", null);
        }
        return new Result(count);
    }

    public Result<Integer> endActivity(String productId) {
        Integer count = 0;
        try {
            count = activityService.endActivity(productId);
        } catch (BizException e) {
            return new Result(ResultCodeConstant.SYSTEM_EXCEPTION, e.getErrorCode(), count);
        } catch (Exception e) {
            return new Result(ResultCodeConstant.SYSTEM_EXCEPTION, "系统异常", null);
        }
        return new Result(count);
    }
}
