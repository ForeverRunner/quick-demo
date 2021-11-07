package com.xxd.ilin.shardingsphere.quickdemo.controller;

import com.xxd.ilin.shardingsphere.quickdemo.controller.dto.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {


    @GetMapping("/sayHello")
    public Result<String> sayHello(@RequestParam("name") String name) {
        return Result.success(name + ",nice to meet you");
    }

    @GetMapping("/sayHello2")
    public Result<String> sayHello2(@RequestParam("name") String name) {
//        int a = 10 / 0;
        if ("dongfang".equals(name)){
            throw new IllegalArgumentException("name不能dongfang");
        }
        return Result.success(name + ",nice to meet you");
    }

}
