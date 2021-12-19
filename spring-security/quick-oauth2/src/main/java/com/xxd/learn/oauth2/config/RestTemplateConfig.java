package com.xxd.learn.oauth2.config;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Configuration
public class RestTemplateConfig {
    @Bean
    RestTemplate restTemplate(ClientHttpRequestFactory clientHttpRequestFactory) {
        RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory);
        List<HttpMessageConverter<?>> messageConverters = restTemplate.getMessageConverters();
        for (HttpMessageConverter c : messageConverters) {
            if (c instanceof StringHttpMessageConverter) {
                ((StringHttpMessageConverter) c).setDefaultCharset(StandardCharsets.UTF_8);
            }
        }

        return restTemplate;
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.rest.http")
    HttpClientProperties httpClientProperties() {
        return new HttpClientProperties();
    }

    @Bean
    ClientHttpRequestFactory clientHttpRequestFactory(HttpClientProperties httpClientProperties) {
        //如果不使用HttpClient的连接池，则使用restTemplate默认的SimpleClientHttpRequestFactory,底层基于HttpURLConnection
        if (!httpClientProperties.isUseHttpClientPool()) {
            SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
            factory.setConnectTimeout(httpClientProperties.getConnectTimeout());
            factory.setReadTimeout(httpClientProperties.getReadTimeout());
            return factory;
        }

        //默认使用连接池 PoolingHttpClientConnectionManager

        HttpClient httpClient = HttpClientBuilder.create()
                .setMaxConnTotal(httpClientProperties.getMaxTotalConnect())
                .setMaxConnPerRoute(httpClientProperties.getMaxConnectPerRoute())
                .evictExpiredConnections()
                .evictIdleConnections(5000, TimeUnit.MILLISECONDS)
                .build();
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient);
        factory.setConnectTimeout(httpClientProperties.getConnectTimeout());
        factory.setReadTimeout(httpClientProperties.getReadTimeout());
        factory.setConnectionRequestTimeout(httpClientProperties.getConnectionRequestTimeout());
        return factory;
    }
}
