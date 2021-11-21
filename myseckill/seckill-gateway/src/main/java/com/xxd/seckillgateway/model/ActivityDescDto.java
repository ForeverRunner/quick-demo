package com.xxd.seckillgateway.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class ActivityDescDto implements Serializable {
    private String activityName;
    private String productId;
    private Date startTime;
    private Date endTime;
    private Integer limitNum;
    private Integer stockNum;
    private BigDecimal price;
    private String statusStr;
}
