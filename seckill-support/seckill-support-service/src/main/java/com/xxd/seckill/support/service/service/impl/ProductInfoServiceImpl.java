package com.xxd.seckill.support.service.service.impl;

import com.xxd.seckill.support.dao.mapper.ProductInfoMapper;
import com.xxd.seckill.support.dao.model.ProductInfo;
import com.xxd.seckill.support.export.dto.ProductInfoDTO;
import com.xxd.seckill.support.export.dto.Result;
import com.xxd.seckill.support.service.service.ProductInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductInfoServiceImpl implements ProductInfoService {

    @Autowired
    private ProductInfoMapper productInfoMapper;

    public Result<Integer> createProduct(ProductInfoDTO productInfoDTO) {
        ProductInfo productInfo = new ProductInfo();
        BeanUtils.copyProperties(productInfoDTO, productInfo);
        int count = productInfoMapper.insert(productInfo);
        BeanUtils.copyProperties(productInfoDTO, productInfo);
        return new Result<Integer>(count);
    }

    public Result<ProductInfoDTO> queryProduct(String productId) {
        ProductInfo productInfo = productInfoMapper.selectByProductId(productId);
        ProductInfoDTO infoDTO = new ProductInfoDTO();
        BeanUtils.copyProperties(productInfo, infoDTO);
        return new Result<ProductInfoDTO>(infoDTO);
    }
}
