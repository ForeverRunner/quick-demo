package com.xxd.seckill.support.dao.mapper;

import com.xxd.seckill.support.dao.model.ProductInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProductInfo record);

    ProductInfo selectByPrimaryKey(Long id);

    List<ProductInfo> selectAll();

    int updateByPrimaryKey(ProductInfo record);

    /**
     * 查询商品
     * @param productId
     * @return
     */
    ProductInfo selectByProductId(@Param("productId") String productId);

    /**
     * 更新商品标签 1：正常商品，2：秒杀商品 3：预约商品
     * @param productId
     * @return
     */
    int updateTag(@Param("productId") String productId,@Param("tag") Integer tag);
}