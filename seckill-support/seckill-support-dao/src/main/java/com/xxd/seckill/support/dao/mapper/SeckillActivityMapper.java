package com.xxd.seckill.support.dao.mapper;

import com.xxd.seckill.support.dao.model.SeckillActivity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SeckillActivityMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SeckillActivity record);

    SeckillActivity selectByPrimaryKey(Long id);

    List<SeckillActivity> selectAll();

    int updateByPrimaryKey(SeckillActivity record);

    /**
     * 查询活动(最近的一场)
     * @param productId
     * @return
     */
    SeckillActivity selectByProductId(@Param("productId") String productId);

    /**
     * 查询活动(按条件)
     * @param productId
     * @return
     */
    SeckillActivity selectByCondition(@Param("productId") String productId,@Param("status") Integer status);

    /**
     * 更新活动状态
     * @param
     * @return
     */
    int updateStatus(@Param("id") Long id, @Param("status") Integer status);

    /**
     * 更新库存
     * @param
     * @return
     */
    int updateStockNum(@Param("id") Long id,@Param("buyNum") Integer buyNum);
}