package com.xxd.seckill.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SettlementSubmitDTO {

    private String code;
    private String message;
    private String payPageUrl;
}
