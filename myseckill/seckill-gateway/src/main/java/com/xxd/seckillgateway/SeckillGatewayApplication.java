package com.xxd.seckillgateway;

import com.xxd.seckillgateway.context.SpringContextUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@Import(SpringContextUtil.class)
@ImportResource("classpath:dubbo/consumer.xml")
public class SeckillGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeckillGatewayApplication.class, args);
    }

}
