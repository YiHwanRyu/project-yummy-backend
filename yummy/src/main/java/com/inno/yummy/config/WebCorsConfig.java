package com.inno.yummy.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebCorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000", "http://localhost:3001") // 허용할 주소
                //.allowedOrigins 여러개 사용하면 적용 안됨. 한개에 여러 주소 입력필요.
                .allowedMethods("*") // 모든 HTTP 메서드에 대해 허용
                .allowCredentials(true) // 토큰관련 설정
                .exposedHeaders("Authorization"); // 허용할 헤더 지정
    }
}