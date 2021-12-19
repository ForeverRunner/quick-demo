package com.xxd.learn.oauth2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@Slf4j
public class App {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
        RestTemplate restTemplate = context.getBean("restTemplate", RestTemplate.class);
        String forObject = restTemplate.getForObject("https://www.baidu.com", String.class);
        log.info(forObject);
    }
}
