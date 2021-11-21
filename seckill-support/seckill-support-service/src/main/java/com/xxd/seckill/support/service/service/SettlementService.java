package com.xxd.seckill.support.service.service;

import com.xxd.seckill.support.export.dto.SettlementDataDTO;
import com.xxd.seckill.support.export.dto.SettlementDataRequestDTO;
import com.xxd.seckill.support.export.dto.SettlementOrderDTO;

public interface SettlementService {

    /**
     * 下单
     * @param orderDTO
     * @return 订单号
     */
    String submitOrder(SettlementOrderDTO orderDTO);


    SettlementDataDTO settlementData(SettlementDataRequestDTO requestDTO);
}
