package com.nt.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
public class CorsConfig {
    
    @Value("${cors.allowed.origins:*}")
    private String allowedOrigins;
    
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        
        // Production ke liye specific origins
        if ("*".equals(allowedOrigins)) {
            config.addAllowedOrigin("*");
        } else {
            Arrays.stream(allowedOrigins.split(","))
                  .forEach(config::addAllowedOrigin);
        }
        
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        config.setAllowCredentials(false); // "*" origin ke liye false chahiye
        
        source.registerCorsConfiguration("/api/**", config);
        return new CorsFilter(source);
    }
}