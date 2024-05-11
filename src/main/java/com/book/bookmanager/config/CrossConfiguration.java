package com.book.bookmanager.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 跨域配置和文件上传路径映射
 */
@Configuration
public class CrossConfiguration implements WebMvcConfigurer {

    @Value("${file.upload.path}")
    private String uploadPath;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 允许跨域的路径
                .allowedOriginPatterns("*") // 允许跨域的源
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE") // 允许请求方法
                .maxAge(168000) // 预检间隔时间
                .allowedHeaders("*") // 允许头部设置
                .allowCredentials(true); // 是否发送cookie
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/uploads/**").addResourceLocations("file:"+uploadPath);
    }
}
