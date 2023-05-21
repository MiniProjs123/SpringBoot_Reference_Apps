package com.example.springboot.dummy.SpringBootSpringWebStarter.integration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import static org.assertj.core.api.Assertions.assertThat;

// The embedded server starts on a random port because of webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
// and the actual port is configured automatically in the base URL for the TestRestTemplate.
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PocIntegration {

    @Autowired
    private TestRestTemplate template;

    @Test
    public void getHelloIntegration() throws Exception {
        ResponseEntity<String> response = template.getForEntity("/", String.class);
        assertThat(response.getBody()).isEqualTo("Basic SpringBoot Spring Web starter!");
    }

}
