package com.xxd.seckill.support.export.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class SeckillActivityDTO implements Serializable {

    private long id;
    private String activityName;
    private String productId;
    private Integer limitNum;
    private Integer stockNum;
    private Integer status;
    private Date startTime;
    private Date endTime;
    private String pictureUrl;
    private BigDecimal price;
}
