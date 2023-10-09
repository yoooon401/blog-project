package com.app.blog.project.client.service.kakao;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

@EnableFeignClients(basePackages = "com.app.blog.project.client.service.kakao")
public class KakaoFeignConfig {

    @Value(" KakaoAK ${service.kakao.auth}")
    private String key;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> requestTemplate.header(HttpHeaders.AUTHORIZATION, key);
    }
}
