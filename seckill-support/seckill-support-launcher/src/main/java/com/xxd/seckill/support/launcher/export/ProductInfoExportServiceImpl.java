package com.xxd.seckill.support.launcher.export;

import com.xxd.seckill.support.export.dto.ProductInfoDTO;
import com.xxd.seckill.support.export.dto.Result;
import com.xxd.seckill.support.export.export.ProductExportService;
import com.xxd.seckill.support.launcher.filter.TraceIdConst;
import com.xxd.seckill.support.service.service.ProductInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.rpc.RpcContext;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class ProductInfoExportServiceImpl implements ProductExportService {

    @Autowired
    ProductInfoService productService;

    public Result<Integer> createProduct(ProductInfoDTO productInfoDTO) {
        return null;
    }

    public Result<ProductInfoDTO> queryProduct(String productId) {
        log.info("trraceId:{}", RpcContext.getContext().getAttachment(TraceIdConst.TRACE_ID));
        return productService.queryProduct(productId);
    }
}
