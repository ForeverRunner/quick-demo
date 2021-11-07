package com.xxd.ilin.shardingsphere.quickdemo.controller;

import com.xxd.ilin.shardingsphere.quickdemo.controller.dto.Result;
import org.junit.jupiter.api.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    /**
     * 先抓去子类异常后去匹配父异常
     * @param request
     * @param response
     * @param e
     * @return
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public Result handlerIllegalArgExption(HttpServletRequest request, HttpServletResponse response, Exception e) {
        response.setStatus(HttpServletResponse.SC_OK);
        return new Result(104000, "serverError", e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public Result handlerRuntimeException(HttpServletRequest request, HttpServletResponse response, Exception e) {
        response.setStatus(HttpServletResponse.SC_OK);
        return new Result(500, "serverError", e.getMessage());
    }


}
