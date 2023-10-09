package com.app.blog.project.client.service.naver;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@EnableFeignClients(basePackages = "com.app.blog.project.client.service.naver")
public class NaverFeignConfig {

    @Value(" ${service.naver.auth.client-id}")
    private String clientId;
    @Value(" ${service.naver.auth.client-secret}")
    private String clientSecret;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> requestTemplate.header("X-Naver-Client-Id", clientId)
                .header("X-Naver-Client-Secret", clientSecret);
    }
}
