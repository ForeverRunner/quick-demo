package com.xxd.ilin.shardingsphere.quickdemo.cglib;

import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {
    public void test1() {
        System.out.println("test1");
        throw new RuntimeException("test1");
    }

    public void test2() {
        System.out.println("test2");
    }
}
