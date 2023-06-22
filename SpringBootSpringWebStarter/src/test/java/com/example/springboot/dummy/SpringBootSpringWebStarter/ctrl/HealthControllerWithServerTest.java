package com.example.springboot.dummy.SpringBootSpringWebStarter.ctrl;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.beans.factory.annotation.Value;
import static org.assertj.core.api.Assertions.assertThat;


/*
    Tests using the server with a real HTTP Request....note the port is generated dynamically
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HealthControllerWithServerTest {

    @Value(value="${local.server.port}")
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void healthCheckMessage() throws Exception {
      assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/",
                String.class)).contains("Basic SpringBoot Spring Web starter!");
    }

}

