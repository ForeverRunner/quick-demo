package com.xxd.ilin.shardingsphere.quickdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RequestLogingConfig {
    @Bean
    public RequestLogginFilter requestLogginFilter(){
        RequestLogginFilter requestLogginFilter=new RequestLogginFilter();
        requestLogginFilter.setIncludePayload(true);
//        requestLogginFilter.setIncludeClientInfo(true);
//        requestLogginFilter.setIncludeHeaders(true);
        return requestLogginFilter;
    }
}
