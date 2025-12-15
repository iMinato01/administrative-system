package com.gg.administrative_system_backend.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
@Configuration
@RequiredArgsConstructor
public class CorsConfig {
private final CorsProps corsProps;
    @Bean
    public CorsConfigurationSource corsConfiguration (){
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(corsProps.origins);
        configuration.setAllowedMethods(corsProps.methods);
        configuration.setAllowedHeaders(corsProps.headers);
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource configurationSource = new UrlBasedCorsConfigurationSource();
        configurationSource.registerCorsConfiguration("/**", configuration);
        return configurationSource;
    }
}
