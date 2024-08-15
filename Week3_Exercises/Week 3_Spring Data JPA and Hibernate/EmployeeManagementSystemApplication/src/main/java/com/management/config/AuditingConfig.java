package com.management.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import java.util.Optional;

@Configuration
@EnableJpaAuditing
public class AuditingConfig {
    
    @Bean
    public AuditorAware<String> auditorProvider() {
        // This example uses a fixed username. In a real application, 
        // you would retrieve the current user's username from the security context.
        return () -> Optional.of("admin");
    }
}
