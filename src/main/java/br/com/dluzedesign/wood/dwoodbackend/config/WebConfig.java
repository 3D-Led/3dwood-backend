package br.com.dluzedesign.wood.dwoodbackend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Value("${cors.origin}")
    private String corOrigin;
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/banner/*")
                .allowedOrigins(corOrigin)
                .allowedMethods("GET");
        registry.addMapping("/category")
                .allowedOrigins(corOrigin)
                .allowedMethods("GET");
        registry.addMapping("/newsletter")
                .allowedOrigins(corOrigin)
                .allowedMethods("POST");
        registry.addMapping("/products/**")
                .allowedOrigins(corOrigin)
                .allowedMethods("GET");
    }
}
