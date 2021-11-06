package com.xxd.ilin.shardingsphere.quickdemo.cglib.springaop;

import com.xxd.ilin.shardingsphere.quickdemo.cglib.UserService;
import com.xxd.ilin.shardingsphere.quickdemo.cglib.UserServiceImpl;
import com.xxd.ilin.shardingsphere.quickdemo.cglib.advice.MyMethodAdvice;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//@Configuration
//@ComponentScan("com.xxd.ilin.shardingsphere.quickdemo.cglib")
public class AopConfig {
    @Bean
    public UserService userService() {
        UserService userService = new UserServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(userService);
        proxyFactory.addAdvice(new MyMethodAdvice());
        return (UserService) proxyFactory.getProxy();
    }
}
