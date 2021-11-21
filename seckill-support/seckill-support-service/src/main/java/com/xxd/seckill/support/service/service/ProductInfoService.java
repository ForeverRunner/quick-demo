package com.xxd.seckill.support.service.service;

import com.xxd.seckill.support.export.dto.ProductInfoDTO;
import com.xxd.seckill.support.export.dto.Result;

public interface ProductInfoService {

    /**
     * 创建商品
     * @param productInfoDTO
     * @return
     */
    Result<Integer> createProduct(ProductInfoDTO productInfoDTO);


    /**
     * 查询商品
     * @param productId
     * @return
     */
    Result<ProductInfoDTO> queryProduct(String productId);
}
