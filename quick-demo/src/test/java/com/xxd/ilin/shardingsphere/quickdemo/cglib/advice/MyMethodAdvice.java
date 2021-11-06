package com.xxd.ilin.shardingsphere.quickdemo.cglib.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
public class MyMethodAdvice implements MethodBeforeAdvice, AfterReturningAdvice, ThrowsAdvice, MethodInterceptor {
    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        System.out.println("afterReturning" + method.getName());
    }

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("before" + method.getName());
    }

    public void afterThrowing(Method method, Object[] args, Object target, RuntimeException runTimeException) {
        System.out.println("afterThrowing" + method.getName());
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("invocation before " + invocation.getMethod().getName());
        Object proceed = invocation.proceed();
        System.out.println("invocation after " + invocation.getMethod().getName());
        return proceed;
    }
}
