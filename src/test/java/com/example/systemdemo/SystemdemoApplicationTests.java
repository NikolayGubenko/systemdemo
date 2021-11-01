package com.example.systemdemo;

import com.example.systemdemo.model.State;
import com.example.systemdemo.service.CommandService;
import com.example.systemdemo.service.ResponseService;
import org.junit.ClassRule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
@ContextConfiguration(initializers = {SystemdemoApplicationTests.Initializer.class})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class SystemdemoApplicationTests {

    @Autowired
    private CommandService commandService;

    @Autowired
    private ResponseService responseService;

    @ClassRule
    public static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres")
            .withDatabaseName("testdb")
            .withUsername("nat")
            .withPassword("root");

    static class Initializer
            implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            postgreSQLContainer.start();
            TestPropertyValues.of(
                    "spring.datasource.url=" + postgreSQLContainer.getJdbcUrl(),
                    "spring.datasource.username=" + postgreSQLContainer.getUsername(),
                    "spring.datasource.password=" + postgreSQLContainer.getPassword(),
                    "spring.jpa.hibernate.ddl-auto=update"
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }

    @Test
    void addOneCommand() {
        final var command = this.commandService.addCommand("fdbac1f4efadc9b1fe2dfca12f44ffab41cdac4b");
        assertEquals(command.getState(), State.NEW);
    }

    @Test
    void trueCommandCheck() {
        this.commandService.addCommand("5dbac1f4efadc9b1fe2dfca12f44ffab41cdac4b");
        final var response = this.responseService.saveResponse("5dbac1f4efadc9b1fe2dfca12f44ffab41cdac4b");
        assertTrue(this.commandService.checkResponse(response.getResponse()));
    }

    @Test
    void changeStatus() {
        final var command = this.commandService.addCommand("cafac1f4efadc9b1fe2dfca12f44ffab41cdac4b");
        final var response = this.responseService.saveResponse("cafac1f4efadc9b1fe2dfca12f44ffab41cdac4b");
        commandService.checkResponse(response.getResponse());
        assertEquals(command.getState(), State.LINKED);
    }

    @Test
    void contextLoads() {
    }
}
