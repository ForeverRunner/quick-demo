package com.xxd.seckill.support.launcher;

import com.xxd.seckill.support.launcher.context.SpringContextUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@MapperScan("com.xxd.seckill.support.dao.mapper")
@ComponentScan(basePackages = {"com.xxd.seckill.support.service"})
@ImportResource("classpath:dubbo/provider.xml")
@Import(SpringContextUtil.class)
public class SupportApp {
    public static void main(String[] args) {
        SpringApplication.run(SupportApp.class, args);
    }
}
