package com.xxd.ilin.shardingsphere.quickdemo.dao;

import com.xxd.ilin.shardingsphere.quickdemo.entity.Course;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

@SpringBootTest
class RedisInnerServiceTest {
    @Autowired
    private RedisInnerService redisInnerService;

    @Test
    public void testSetAndGet() {
        String key = "testFoot";
        String value = "testValue";
        redisInnerService.set(key, value, 100L, TimeUnit.HOURS);
        Assert.assertEquals(value, redisInnerService.get(key));
    }

    @Test
    public void testSetAndGetObject() {
        Course course = new Course();
        Long cid = 1001L;
        course.setCid(cid);
        course.setCstatus("online");
        course.setCname("计算机基础");
        course.setUserId(1001L);
        String key = "course:stu.info." + cid;
        redisInnerService.set(key, course);
        Course courseFromCache = redisInnerService.get(key, Course.class);
        System.out.println(courseFromCache);
    }
}