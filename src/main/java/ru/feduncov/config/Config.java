package ru.feduncov.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.annotation.RequestScope;

@Configuration
public class Config {

    @Bean
    @RequestScope
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
