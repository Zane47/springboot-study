package com.imooc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    public CorsConfig() {

    }

    @Bean
    public CorsFilter corsFilter() {
        // 1. 添加cors配置信息
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.addAllowedOrigin("http://localhost:8080");

        // 请求数据是否可以携带内容, 是否可以发送cookie信息
        corsConfig.setAllowCredentials(true);

        // 设置允许请求的方式
        corsConfig.addAllowedMethod("*");

        // 设置允许的header
        corsConfig.addAllowedHeader("*");

        // 2. 为url添加映射路径
        // /**: 所有的路由
        UrlBasedCorsConfigurationSource corsConfigSource = new UrlBasedCorsConfigurationSource();
        corsConfigSource.registerCorsConfiguration("/**", corsConfig);

        return new CorsFilter(corsConfigSource);
    }
}
