package com.xxd.ilin.shardingsphere.quickdemo.cglib.springaop;

import com.xxd.ilin.shardingsphere.quickdemo.cglib.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringAopTest {
    @Test
    public void testSpringContainerProxyFactory() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AopConfig.class);
        UserService userService = (UserService) context.getBean("userService");
        userService.test1();
    }

    @Test
    public void testBeanNameFactoryAuto() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BeanNameAutoAopConfig.class);
        UserService userService = (UserService) context.getBean("userServiceImpl");
        userService.test1();
    }
}
