package com.example.todobackend;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration implements WebMvcConfigurer {

    // TODO 1 : configure Spring MVC to allow CORS requests from domains "app-a.nasa.gov", "tracking.roscosmos.ru" and "internal.spacex.com", on all /api/ mappings
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
                .addMapping("/api/**");
    }
}
