package com.cyberfreak.services.config.persistence;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAwareImpl")
@EnableJpaRepositories(basePackages = "com.cyberfreak.services.repository")
@EntityScan(basePackages = "com.cyberfreak.services.domain")
public class PersistenceConfig {
}
