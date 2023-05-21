package com.example.springboot.dummy.SpringBootSpringWebStarter.service;

import com.example.springboot.dummy.SpringBootSpringWebStarter.ctrl.GreetingController;
import com.example.springboot.dummy.SpringBootSpringWebStarter.ex.BadIdeaException;
import com.example.springboot.dummy.SpringBootSpringWebStarter.ex.NotImplementedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class GreetingService implements Greeter {

    public static final String BUSINESS = "business";
    public static final String PERSONAL = "personal";
    protected static final List<String> VALID_CLASSIFICATIONS = Arrays.asList(PERSONAL, BUSINESS);

    private Logger logger = LoggerFactory.getLogger(GreetingService.class);

    @Override
    public String getGreeting(int id, String type) {

        validateGreetingId(id);
        validateGreetingType(type);

        return "GOODDAY Jose! - id = " + id;
    }

    private void validateGreetingId(int id) {
        logger.info("validating greeting id....");
        if (id == 100) {
            throw new NotImplementedException("not built yet");
        }
        logger.info("no issues with classification!");
    }

    private void validateGreetingType(String type) {
        logger.info("validating greeting type....");
        boolean isValid =
                VALID_CLASSIFICATIONS.stream().filter(s->s.equals(type)).findFirst().isPresent();
        if (!isValid) {
            throw new BadIdeaException("Invalid greeting classification - type = " + type);
        }
        logger.info("id is fine - it is not 100!");
    }

}
