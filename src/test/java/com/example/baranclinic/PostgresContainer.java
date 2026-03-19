package com.example.baranclinic;

import org.testcontainers.containers.PostgreSQLContainer;

public class PostgresContainer {

    private static final PostgreSQLContainer<?> INSTANCE =
            new PostgreSQLContainer<>("postgres:15")
                    .withDatabaseName("testdb")
                    .withUsername("test_user")
                    .withPassword("test_password");

    static {
        INSTANCE.start();
    }

    public static PostgreSQLContainer<?> getInstance() {
        return INSTANCE;
    }
}
