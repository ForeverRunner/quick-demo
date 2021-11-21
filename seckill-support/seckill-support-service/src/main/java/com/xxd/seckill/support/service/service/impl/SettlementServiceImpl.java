package com.xxd.seckill.support.service.service.impl;

import com.xxd.seckill.support.dao.mapper.OrderRecordMapper;
import com.xxd.seckill.support.dao.mapper.ProductInfoMapper;
import com.xxd.seckill.support.dao.mapper.SeckillActivityMapper;
import com.xxd.seckill.support.dao.model.OrderRecord;
import com.xxd.seckill.support.dao.model.ProductInfo;
import com.xxd.seckill.support.dao.model.SeckillActivity;
import com.xxd.seckill.support.export.dto.SettlementDataDTO;
import com.xxd.seckill.support.export.dto.SettlementDataRequestDTO;
import com.xxd.seckill.support.export.dto.SettlementOrderDTO;
import com.xxd.seckill.support.service.service.SettlementService;
import com.xxd.seckill.support.service.tool.RedisTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Random;

@Service
@Slf4j
public class SettlementServiceImpl implements SettlementService {
    @Autowired
    private OrderRecordMapper orderRecordMapper;

    @Autowired
    private SeckillActivityMapper activityMapper;

    @Autowired
    private ProductInfoMapper productInfoMapper;

    @Autowired
    RedisTool redisTool;

    public String submitOrder(SettlementOrderDTO orderDTO) {
        //1.校验商品标识
        ProductInfo productInfo = productInfoMapper.selectByProductId(orderDTO.getProductId());
        Integer tag = productInfo.getTag();
        //2.限购
        Long count = redisTool.eval("store_" + orderDTO.getProductId(), String.valueOf(orderDTO.getBuyNum()));
        log.error("用户{}限购结果：{}", orderDTO.getUserId(), count);
        if (count == null || count <= 0) {
            return null;
        }

        //3.下单-初始化
        Random random = new Random(10000);
        String orderId = String.valueOf(System.currentTimeMillis()) + random.nextInt();
        OrderRecord orderRecord = new OrderRecord();

        SeckillActivity activityInfo = activityMapper.selectByProductId(orderDTO.getProductId());

        orderRecord.setOrderId(orderId);
        BigDecimal orderPrice = activityInfo.getPrice().multiply(new BigDecimal(orderDTO.getBuyNum()));
        orderRecord.setOrderPrice(orderPrice);
        orderRecord.setOrderStatus(0);
        orderRecord.setAddress(orderDTO.getAddress());
        orderRecord.setPayType(orderDTO.getPayType());
        orderRecord.setProductId(orderDTO.getProductId());
        orderRecord.setUserId(orderDTO.getUserId());
        orderRecord.setOrderTime(new Date());
        orderRecord.setBuyNum(orderDTO.getBuyNum());

        orderRecordMapper.insert(orderRecord);

        //3.预占库存
        activityMapper.updateStockNum(activityInfo.getId(), orderDTO.getBuyNum());

        //4.更新订单-下单成功待支付
        orderRecordMapper.updateOrderStatus(orderId, 1);

        return orderId;
    }

    public SettlementDataDTO settlementData(SettlementDataRequestDTO requestDTO) {
        SeckillActivity activityInfo = activityMapper.selectByProductId(requestDTO.getProductId());

        SettlementDataDTO dataDTO = new SettlementDataDTO();
        dataDTO.setAssets("");
        dataDTO.setPayType(1);//在线支付
        dataDTO.setTotalPrice(activityInfo.getPrice().multiply(new BigDecimal(requestDTO.getBuyNum())));
        dataDTO.setAddress("北京朝阳区");

        return dataDTO;
    }
}
