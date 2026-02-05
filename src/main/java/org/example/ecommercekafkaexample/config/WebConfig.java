package org.example.ecommercekafkaexample.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*") // zmień na produkcyjne domeny jeśli trzeba
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
    }
}
