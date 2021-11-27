package com.xxd.seckill.service;

import com.xxd.seckill.support.common.exception.BizException;
import com.xxd.seckill.support.export.dto.SettlementOrderDTO;
import com.xxd.seckill.dto.SettlementInitDTO;
import com.xxd.seckill.dto.SettlementSubmitDTO;

import java.util.Map;

public interface SettlementService {
    SettlementInitDTO initData(String productId, String buyNum) throws BizException;

    Map<String, Object> dependency();

    SettlementSubmitDTO submitOrder(SettlementOrderDTO requestDTO) throws BizException;
}
