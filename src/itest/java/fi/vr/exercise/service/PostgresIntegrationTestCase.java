package fi.vr.exercise.service;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

/**
 * A base class for integration tests that require Postgres container.
 *
 * @author Juha-Pekka Koivisto
 */
@SpringBootTest
@ActiveProfiles("default")
public class PostgresIntegrationTestCase {

  private static final PostgreSQLContainer<?> postgres =
    // version should match that of target env
    new PostgreSQLContainer<>(DockerImageName.parse("postgres:16"));

  @Autowired
  JdbcTemplate jdbcTemplate;

  @BeforeAll
  public static void startContainer() {
    postgres.start();
  }

  @AfterAll
  public static void stopContainer() {
    postgres.stop();
  }

  @DynamicPropertySource
  static void configureProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", postgres::getJdbcUrl);
    registry.add("spring.datasource.username", postgres::getUsername);
    registry.add("spring.datasource.password", postgres::getPassword);
  }

  @BeforeEach
  public void cleanDatabase() {
    jdbcTemplate.update("DELETE FROM message_template");
  }

}
