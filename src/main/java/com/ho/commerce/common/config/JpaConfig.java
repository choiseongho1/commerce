package com.ho.commerce.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@Configuration
public class JpaConfig {
    @Bean
    public AuditorAware<String> auditorAware(){ //AuditorAware을 빈으로 등록
        return new AuditorAwareImpl();
    }
}
