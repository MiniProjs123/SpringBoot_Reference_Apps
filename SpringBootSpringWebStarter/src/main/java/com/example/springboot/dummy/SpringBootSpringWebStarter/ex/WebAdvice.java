package com.example.springboot.dummy.SpringBootSpringWebStarter.ex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class WebAdvice {
    private Logger logger = LoggerFactory.getLogger(WebAdvice.class);

    @ResponseBody
    @ExceptionHandler(NotImplementedException.class)
    @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
    String notImplementedHandler(NotImplementedException e) {
        logger.info(e.getMessage(), e);
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(BadIdeaException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String badIdeaHandler(BadIdeaException e) {
        logger.info(e.getMessage(), e);
        return e.getMessage();
    }

}
