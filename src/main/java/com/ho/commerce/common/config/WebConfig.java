package com.ho.commerce.common.config;

import com.ho.commerce.common.interceptor.BaseDtoInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Slf4j
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private BaseDtoInterceptor baseDtoInterceptor;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        log.info("Adding CORS mappings");
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(3600);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        log.info("Adding interceptors");
        registry.addInterceptor(baseDtoInterceptor)
                .addPathPatterns("/**");
    }
}
