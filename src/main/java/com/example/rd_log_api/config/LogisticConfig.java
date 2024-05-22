package com.example.rd_log_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class LogisticConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
