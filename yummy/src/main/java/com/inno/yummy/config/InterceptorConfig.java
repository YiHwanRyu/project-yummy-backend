package com.inno.yummy.config;

import com.inno.yummy.interceptor.LoginCheckInterceptor;
import com.inno.yummy.jwt.JwtUtil;
import com.inno.yummy.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class InterceptorConfig implements WebMvcConfigurer {
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginCheckInterceptor(jwtUtil, userRepository))
                .addPathPatterns("/**")
                .excludePathPatterns("/api/auth/**", "/error");
    }
}
