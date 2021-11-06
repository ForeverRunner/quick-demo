package com.xxd.ilin.shardingsphere.quickdemo.cglib;

import com.xxd.ilin.shardingsphere.quickdemo.cglib.advice.MyMethodAdvice;
import org.aopalliance.aop.Advice;
import org.junit.Test;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.PointcutAdvisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.springframework.cglib.proxy.*;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class QuickStartTests {

    @Test
    public void testCglib() {
        UserService userService = new UserServiceImpl();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserService.class);
        enhancer.setCallbacks(new Callback[]{
                new MethodInterceptor() {
                    @Override
                    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                        System.out.println("before");
                        Object result = method.invoke(userService, objects);
                        System.out.println("after");
                        return result;
                    }
                }, NoOp.INSTANCE
        });
        enhancer.setCallbackFilter(new CallbackFilter() {
            @Override
            public int accept(Method method) {
                return 0;
            }
        });
        UserService proxy = (UserService) enhancer.create();

        proxy.test1();
    }

    @Test
    public void testJDK() {
        UserService userServiceProxy = (UserService) Proxy.newProxyInstance(UserService.class.getClassLoader(), new Class[]{UserService.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return null;
            }
        });
        userServiceProxy.test1();

    }

    @Test
    public void testSpringProxy() {
        UserServiceImpl userService = new UserServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(userService);
        //类型:指定接口JDK动态代理；不加，CGLIB代理
        //proxyFactory.setInterfaces(UserService.class);
        //ThrowAdvice BeforeAdvice
        proxyFactory.addAdvice(new MethodBeforeAdvice() {
            @Override
            public void before(Method method, Object[] objects, Object o) throws Throwable {
                System.out.println("before spring");
            }
        });
        UserServiceImpl userServiceProxy = (UserServiceImpl) proxyFactory.getProxy();
        userServiceProxy.test1();
        System.out.println(userServiceProxy);
    }

    @Test
    public void testAdvices() {
        UserServiceImpl userService = new UserServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(userService);
        //类型:指定接口JDK动态代理；不加，CGLIB代理
        //proxyFactory.setInterfaces(UserService.class);
        //ThrowAdvice BeforeAdvice
        proxyFactory.addAdvice(new MyMethodAdvice());
        UserServiceImpl userServiceProxy = (UserServiceImpl) proxyFactory.getProxy();
        userServiceProxy.test2();
        //System.out.println(userServiceProxy);
    }

    @Test
    public void testAdvisor() {
        UserServiceImpl userService = new UserServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(userService);
        //类型:指定接口JDK动态代理；不加，CGLIB代理
        proxyFactory.setInterfaces(UserService.class);
        //ThrowAdvice BeforeAdvice
        //advisor=advice+pointcut
        proxyFactory.addAdvisor(new PointcutAdvisor() {
            @Override
            public Pointcut getPointcut() {
                return new StaticMethodMatcherPointcut() {
                    @Override
                    public boolean matches(Method method, Class<?> targetClass) {
                        return method.getName().endsWith("2");
                    }
                };
            }

            @Override
            public Advice getAdvice() {
                return new MyMethodAdvice();
            }

            @Override
            public boolean isPerInstance() {
                return false;
            }
        });
        UserServiceImpl userServiceProxy = (UserServiceImpl) proxyFactory.getProxy();
        userServiceProxy.test2();
        userServiceProxy.test1();
        //System.out.println(userServiceProxy);
    }
}
