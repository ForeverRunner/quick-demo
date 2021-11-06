package com.xxd.ilin.shardingsphere.quickdemo.cglib.springaop;

import com.xxd.ilin.shardingsphere.quickdemo.cglib.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringAop {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AopConfig.class);
        UserService userService = (UserService) context.getBean("userService");
        userService.test1();
    }
}
