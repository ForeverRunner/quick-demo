package com.xxd.biz.dto;

import java.io.Serializable;

public class Result<T> implements Serializable {
    private int code;
    private String message;
    private T data;

    public Result(int code, String message, T t) {
        this.code = code;
        this.message = message;
        this.data = t;
    }

    public static Result success(Object t) {
        return new Result(200, "", t);
    }

    public static Result fail(int errorCode, String msg) {
        return new Result(errorCode, msg, null);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
