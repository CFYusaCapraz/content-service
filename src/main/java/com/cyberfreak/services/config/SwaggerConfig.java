package com.cyberfreak.services.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi contentApi() {
        return GroupedOpenApi.builder()
                .group("content-v1-content")
                .displayName("Content API")
                .pathsToMatch("/api/v1/*contents/**").build();
    }

    @Bean
    public GroupedOpenApi applicationApi() {
        return GroupedOpenApi.builder()
                .group("content-v1-application")
                .displayName("Application API")
                .pathsToMatch("/api/v1/applications/**").build();
    }
}
