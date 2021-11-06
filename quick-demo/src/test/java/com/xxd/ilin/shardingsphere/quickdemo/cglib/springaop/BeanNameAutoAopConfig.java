package com.xxd.ilin.shardingsphere.quickdemo.cglib.springaop;

import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.xxd.ilin.shardingsphere.quickdemo.cglib")
public class BeanNameAutoAopConfig {

    //DefaultAdvisorAutoProxyCreator
    @Bean
    public BeanNameAutoProxyCreator beanNameAutoProxyCreator() {
        BeanNameAutoProxyCreator beanNameAutoProxyCreator = new BeanNameAutoProxyCreator();
        beanNameAutoProxyCreator.setBeanNames("userService*");
        beanNameAutoProxyCreator.setInterceptorNames("myMethodAdvice");
        beanNameAutoProxyCreator.setProxyTargetClass(true);
        return beanNameAutoProxyCreator;
    }
}
