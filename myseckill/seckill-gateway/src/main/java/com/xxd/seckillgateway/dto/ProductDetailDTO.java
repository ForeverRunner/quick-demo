package com.xxd.seckillgateway.dto;

import lombok.Data;

@Data
public class ProductDetailDTO {

    private String productName;
    private String productPrice;
    private String productPictureUrl;
    private Integer tag;
    private Integer isAvailable;

}
