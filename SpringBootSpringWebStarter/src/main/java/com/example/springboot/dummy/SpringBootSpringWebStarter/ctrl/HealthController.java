package com.example.springboot.dummy.SpringBootSpringWebStarter.ctrl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


// http://localhost:8080/

@RestController
public class HealthController {

    private Logger logger = LoggerFactory.getLogger(HealthController.class);

    @GetMapping("/")
    public String index() {
        return "Basic SpringBoot Spring Web starter!";
    }

}


