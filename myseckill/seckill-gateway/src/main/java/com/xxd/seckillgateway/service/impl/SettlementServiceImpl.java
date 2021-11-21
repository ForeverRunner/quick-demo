package com.xxd.seckillgateway.service.impl;

import com.alibaba.fastjson.JSON;
import com.xxd.seckill.support.common.exception.BizException;
import com.xxd.seckill.support.export.constant.ResultCodeConstant;
import com.xxd.seckill.support.export.dto.*;
import com.xxd.seckill.support.export.export.ActivityExportService;
import com.xxd.seckill.support.export.export.SettlementExportService;
import com.xxd.seckillgateway.dto.SettlementInitDTO;
import com.xxd.seckillgateway.dto.SettlementSubmitDTO;
import com.xxd.seckillgateway.service.SettlementService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Service
@Slf4j
public class SettlementServiceImpl implements SettlementService {
    @Autowired
    private ActivityExportService activityExportService;

    @Autowired
    private SettlementExportService settlementExportService;

    public SettlementInitDTO initData(String productId, String buyNum) throws BizException {
        SettlementInitDTO settlementInitDTO = new SettlementInitDTO();
        if (StringUtils.isBlank(productId) || StringUtils.isBlank(buyNum)) {
            throw new BizException("活动入参不合法");
        }
        //校验活动有效性
        Result<SeckillActivityDTO> seckillActivityDTOResult = activityExportService.queryActivity(productId);
        log.info("结算页面查询活动出参数:{}", JSON.toJSONString(seckillActivityDTOResult));
        if (seckillActivityDTOResult == null) {
            throw new BizException("系统异常");
        }
        if (!StringUtils.endsWithIgnoreCase(seckillActivityDTOResult.getCode(), ResultCodeConstant.SUCCESS)) {
            throw new BizException(seckillActivityDTOResult.getMessage());
        }
        SeckillActivityDTO seckillActivityDTO = seckillActivityDTOResult.getData();
        int buyNumInt = Integer.parseInt(buyNum);
        if (buyNumInt > seckillActivityDTO.getLimitNum()) {
            throw new BizException("超过了单次限购数量");
        }
        if (buyNumInt > seckillActivityDTO.getStockNum()) {
            throw new BizException("商品已售完");
        }
        Date now = new Date();
        if (now.before(seckillActivityDTO.getStartTime()) || now.after(seckillActivityDTO.getEndTime())) {
            throw new BizException("活动不在有效期");
        }
        //设置活动商品相关信息
        settlementInitDTO.setLimitNum(String.valueOf(seckillActivityDTO.getLimitNum()));
        settlementInitDTO.setActivityName(seckillActivityDTO.getActivityName());
        settlementInitDTO.setProductId(seckillActivityDTO.getProductId());
        settlementInitDTO.setProductPictureUrl(seckillActivityDTO.getPictureUrl());
        settlementInitDTO.setProductPrice(seckillActivityDTO.getPrice().toPlainString());

        //调用初始化接口
        SettlementDataRequestDTO settlementDataRequestDTO = new SettlementDataRequestDTO();
        settlementDataRequestDTO.setUserId(UUID.randomUUID().toString());
        settlementDataRequestDTO.setBuyNum(buyNumInt);
        settlementDataRequestDTO.setProductId(productId);
        Result<SettlementDataDTO> settlementDataDTOResult = settlementExportService.settlementData(settlementDataRequestDTO);
        log.info("结算页面初始化:{}", JSON.toJSONString(settlementDataDTOResult));
        if (settlementDataDTOResult == null) {
            throw new BizException("系统异常");
        }
        if (!StringUtils.endsWithIgnoreCase(settlementDataDTOResult.getCode(), ResultCodeConstant.SUCCESS)) {
            throw new BizException(settlementDataDTOResult.getMessage());
        }
        SettlementDataDTO settlementDataDTO = settlementDataDTOResult.getData();
        settlementInitDTO.setTotalPrice(settlementDataDTO.getTotalPrice().toPlainString());
        settlementInitDTO.setPayType(String.valueOf(settlementDataDTO.getPayType()));
        settlementInitDTO.setAddress(settlementDataDTO.getAddress());
        return settlementInitDTO;
    }

    public Map<String, Object> dependency() {
        return null;
    }

    public SettlementSubmitDTO submitOrder(SettlementOrderDTO requestDTO) throws BizException {
        //校验活动有效性
        Result<SeckillActivityDTO> seckillActivityDTOResult = activityExportService.queryActivity(requestDTO.getProductId());
        log.info("提交订单页面查询活动出参数:{}", JSON.toJSONString(seckillActivityDTOResult));
        if (seckillActivityDTOResult == null) {
            throw new BizException("系统异常");
        }
        if (!StringUtils.endsWithIgnoreCase(seckillActivityDTOResult.getCode(), ResultCodeConstant.SUCCESS)) {
            throw new BizException(seckillActivityDTOResult.getMessage());
        }
        SeckillActivityDTO seckillActivityDTO = seckillActivityDTOResult.getData();
        int buyNumInt = requestDTO.getBuyNum();
        if (buyNumInt > seckillActivityDTO.getLimitNum()) {
            throw new BizException("超过了单次限购数量");
        }
        if (buyNumInt > seckillActivityDTO.getStockNum()) {
            throw new BizException("商品已售完");
        }
        Date now = new Date();
        if (now.before(seckillActivityDTO.getStartTime()) || now.after(seckillActivityDTO.getEndTime())) {
            throw new BizException("活动不在有效期");
        }
        requestDTO.setUserId(UUID.randomUUID().toString());
        //3.提交订单
        requestDTO.setUserId(UUID.randomUUID().toString());
        Result<String> orderResult = settlementExportService.submitOrder(requestDTO);
        if(orderResult == null){
            throw new BizException("系统异常");
        }
        if(!StringUtils.endsWithIgnoreCase(orderResult.getCode(), ResultCodeConstant.SUCCESS)){
            throw new BizException(orderResult.getMessage());
        }
        if(StringUtils.isEmpty(orderResult.getData())){
            throw new BizException("抢购失败");
        }

        //4.根据订单号获取支付URL
        Result<String> payPageUrlResult = settlementExportService.getPayPageUrl(orderResult.getData());
        if(payPageUrlResult == null){
            throw new BizException("系统异常");
        }
        if(!StringUtils.endsWithIgnoreCase(payPageUrlResult.getCode(), ResultCodeConstant.SUCCESS)){
            throw new BizException(payPageUrlResult.getMessage());
        }

        //5.其他：包括一些数据统计等，可通过消息来解耦完成

        return new SettlementSubmitDTO("000000","",payPageUrlResult.getData());
    }
}
