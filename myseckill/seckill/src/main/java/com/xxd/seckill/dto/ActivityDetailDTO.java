package com.xxd.seckill.dto;

import lombok.Data;

@Data
public class ActivityDetailDTO {

    private String productName;
    private String productPrice;
    private String productPictureUrl;
    private Integer isAvailable;
}
