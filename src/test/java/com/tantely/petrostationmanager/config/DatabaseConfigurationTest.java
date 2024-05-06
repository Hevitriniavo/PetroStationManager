package com.tantely.petrostationmanager.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class DatabaseConfigurationTest {
    @Autowired
    private DatabaseConfiguration databaseConfiguration;


    @Test
    void testGetConnection() {
        assertNotNull(databaseConfiguration.getConnection());
    }

    @Test
    void testGetUrl() {
        assertNotNull(databaseConfiguration.getUrl());
    }

    @Test
    void testGetUsername() {
        assertNotNull(databaseConfiguration.getUsername());
    }

    @Test
    void testGetPassword() {
        assertNotNull(databaseConfiguration.getPassword());
    }

    @Test
    void verifyConnectionThrowsDatabaseException() {
        assertDoesNotThrow(() -> {
            databaseConfiguration.getConnection();
        });
    }

}