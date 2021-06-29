package integration;

import com.example.planetmanagement.dto.PlanetDto;
import com.example.planetmanagement.utils.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegrationTest {

    private static RestTemplate restTemplate = null;
    @LocalServerPort
    private int port;
    private String baseUrl = "http://localhost";

    @BeforeAll
    public static void init() {
        restTemplate = new RestTemplate();
    }

    @BeforeEach
    public void setUp() {
        baseUrl = baseUrl.concat(":").concat(port+ "").concat("/planets");
    }

    @Test
    @Sql(statements = "INSERT INTO TESTDB.PLANET (id, average_surface_temperature, distance_from_sun, mass, name, surface_area) VALUES (1, 3, 5, 6, 'test', 5)",
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = "DELETE FROM TESTDB.PLANET",
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void returnAPersonWithIdOne() {

        Response<PlanetDto> responsePlanetDto = restTemplate.getForObject(baseUrl.concat("/{id}"), Response.class, 1);
        assertAll(
                () -> Assertions.assertNotNull(responsePlanetDto.getData()),
                () -> Assertions.assertEquals("test", responsePlanetDto.getData().getName())
        );
    }

}
