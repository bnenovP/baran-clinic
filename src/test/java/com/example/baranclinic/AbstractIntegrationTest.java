package com.example.baranclinic;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

@ActiveProfiles("test")
@Testcontainers
public abstract class AbstractIntegrationTest {

    // Disable Ryuk for CI/Docker-in-Docker
    static {
        System.setProperty("testcontainers Ryuk disabled", "true");
    }

    @Container
    protected static final PostgreSQLContainer<?> POSTGRES = new PostgreSQLContainer<>("postgres:15")
            .withDatabaseName("testdb")
            .withUsername("test_user")
            .withPassword("test_password")
            .withReuse(true);

    static {
        // Disable Ryuk in CI
        System.setProperty("testcontainers Ryuk disabled", "true");
        POSTGRES.start();
    }

    @DynamicPropertySource
    static void registerPgProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", POSTGRES::getJdbcUrl);
        registry.add("spring.datasource.username", POSTGRES::getUsername);
        registry.add("spring.datasource.password", POSTGRES::getPassword);
    }
}
