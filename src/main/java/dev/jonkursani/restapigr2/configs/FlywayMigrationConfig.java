package dev.jonkursani.restapigr2.configs;

import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlywayMigrationConfig {
    @Bean
    public FlywayMigrationStrategy repairFlyway() {
        return flyway -> {
            flyway.migrate();
            flyway.repair();
        };
    }
}