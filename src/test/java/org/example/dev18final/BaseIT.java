package org.example.dev18final;

import org.example.dev18final.config.TestcontainersConfig;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

@Import(TestcontainersConfig.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = {TestcontainersConfig.class})
public class BaseIT {
    public static final String SERVER_BASE_URL = "http://localhost:";
    public static final String API_BASE_URL = "/api/v1";
    @LocalServerPort
    protected int port;

    protected TestRestTemplate restTemplate = new TestRestTemplate();
    protected ObjectMapper objectMapper = new ObjectMapper();


    @DynamicPropertySource
    static void configureTestProperties(DynamicPropertyRegistry registry) {
        PostgreSQLContainer<?> postgreSQLContainer = TestcontainersConfig.postgreSQLContainer;
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
        registry.add("spring.jpa.hibernate.ddl-auto", () -> "validate");
    }
}
