package com.xxd.ilin.shardingsphere.quickdemo.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Result<T> {
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 描述信息
     */
    private String msg;
    /**
     * 数据
     */
    private T data;

    public static Result success(Object data) {
        return new Result(200, "success", data);
    }
}
