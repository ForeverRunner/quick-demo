package com.xxd.seckill.support.launcher.export;

import com.xxd.seckill.support.dao.mapper.OrderRecordMapper;
import com.xxd.seckill.support.dao.model.OrderRecord;
import com.xxd.seckill.support.export.dto.Result;
import com.xxd.seckill.support.export.dto.SettlementDataDTO;
import com.xxd.seckill.support.export.dto.SettlementDataRequestDTO;
import com.xxd.seckill.support.export.dto.SettlementOrderDTO;
import com.xxd.seckill.support.export.export.SettlementExportService;
import com.xxd.seckill.support.service.service.ActivityService;
import com.xxd.seckill.support.service.service.SettlementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class SettlementExportServiceImpl implements SettlementExportService {
    @Autowired
    ActivityService activityService;

    @Autowired
    SettlementService settlementService;

    @Autowired
    OrderRecordMapper orderRecordMapper;

    public Result<SettlementDataDTO> settlementData(SettlementDataRequestDTO requestDTO) {
        SettlementDataDTO dataDTO = settlementService.settlementData(requestDTO);
        return new Result(dataDTO);
    }

    public Result<String> submitOrder(SettlementOrderDTO orderDTO) {
        try {
            String orderId = settlementService.submitOrder(orderDTO);

            return new Result(orderId);
        } catch (Exception e) {
            log.error("提单异常", e);
        }
        return new Result(null);
    }

    public Result<String> getPayPageUrl(String orderId) {
        OrderRecord orderRecord = orderRecordMapper.selectByOrderId(orderId);
        String payPageUrl = "http://localhost:8080/mock/payPage?orderId=" + orderId + "&orderPrice=" + orderRecord.getOrderPrice().toPlainString();
        return new Result(payPageUrl);
    }
}
