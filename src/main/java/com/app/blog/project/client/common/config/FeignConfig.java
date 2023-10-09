package com.app.blog.project.client.common.config;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

@EnableFeignClients(basePackages = "com.app.blog.project.client")
@Configuration
public class FeignConfig {

}
