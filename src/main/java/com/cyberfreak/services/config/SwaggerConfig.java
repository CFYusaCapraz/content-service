package com.cyberfreak.services.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.HeaderParameter;
import io.swagger.v3.oas.models.parameters.Parameter;
import org.springdoc.core.customizers.GlobalOpenApiCustomizer;
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
                .pathsToMatch("/api/v1/*contents/**")
                .build();
    }

    @Bean
    public GroupedOpenApi applicationApi() {
        return GroupedOpenApi.builder()
                .group("content-v1-application")
                .displayName("Application API")
                .pathsToMatch("/api/v1/applications/**").build();
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addParameters("x-user-id",
                                new Parameter()
                                .description("User ID for the audit context. This is a mandatory header, because audit information is persisted using this user ID")
                                .in("header")
                                .name("x-user-id")
                                .required(true)
                                .schema(new StringSchema()))
                );
    }

    @Bean
    public GlobalOpenApiCustomizer customerGlobalHeaderOpenApiCustomizer() {
        return openApi -> openApi.getPaths().values().stream().flatMap(pathItem -> pathItem.readOperations().stream())
                .forEach(operation -> operation.addParametersItem(new HeaderParameter().$ref("#/components/parameters/x-user-id")));
    }

}
