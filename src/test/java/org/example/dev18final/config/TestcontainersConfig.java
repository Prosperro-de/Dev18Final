package org.example.dev18final.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

@TestConfiguration(proxyBeanMethods = false)
@Testcontainers
public class TestcontainersConfig {

    public static final PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:15.0")
            .withDatabaseName("postgres")
            .withUsername("postgres")
            .withPassword("password");

    static {
        postgreSQLContainer.start();
    }

    @Bean
    public PostgreSQLContainer<?> postgreSQLContainer() {

        return postgreSQLContainer;
    }
}
