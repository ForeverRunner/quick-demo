package com.xxd.ilin.shardingsphere.quickdemo.controller;

import com.xxd.ilin.shardingsphere.quickdemo.controller.dto.Result;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    public Result handlerRuntimeException(HttpServletRequest request, HttpServletResponse response,Exception e){
        response.setStatus(HttpServletResponse.SC_OK);
        return new Result(500,"serverError",e.getMessage());
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity(new Result(status.value(),"出现异常了",ex.getMessage()),status);
    }
}
