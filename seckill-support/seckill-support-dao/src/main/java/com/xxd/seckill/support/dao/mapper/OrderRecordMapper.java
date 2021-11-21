package com.xxd.seckill.support.dao.mapper;

import com.xxd.seckill.support.dao.model.OrderRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OrderRecord record);

    OrderRecord selectByPrimaryKey(Long id);

    List<OrderRecord> selectAll();

    int updateByPrimaryKey(OrderRecord record);

    /**
     * 查询活动
     * @param
     * @return
     */
    OrderRecord selectByOrderId(String orderId);

    /**
     * 更新状态
     * @param
     * @return
     */
    int updateOrderStatus(@Param("orderId") String orderId, @Param("orderStatus") Integer orderStatus);
}