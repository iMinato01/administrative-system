package com.gg.administrative_system_backend.config;

import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@ConfigurationProperties(prefix = "spring.cors")
@Setter
public class CorsProps {
    List<String> origins;
    List<String> methods;
    List<String> headers;
}
