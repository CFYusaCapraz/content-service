package com.cyberfreak.services.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EntityScan(basePackages = "com.cyberfreak.service.domain")
public class MainConfig {
}
