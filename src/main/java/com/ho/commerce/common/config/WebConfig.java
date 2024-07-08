package com.ho.commerce.common.config;

import com.ho.commerce.common.interceptor.BaseDtoInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Slf4j
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private BaseDtoInterceptor baseDtoInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(baseDtoInterceptor)
                .addPathPatterns("/**"); // 모든 경로에 대해 인터셉터 적용
    }
}
