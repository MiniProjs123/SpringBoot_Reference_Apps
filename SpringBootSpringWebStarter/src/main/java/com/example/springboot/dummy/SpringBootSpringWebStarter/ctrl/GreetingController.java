package com.example.springboot.dummy.SpringBootSpringWebStarter.ctrl;
import com.example.springboot.dummy.SpringBootSpringWebStarter.service.GreetingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// GOOD
//  GET http://localhost:8080/greeting/initial/business/ref/470
//  GET http://localhost:8080/greeting/initial/employees
//  DELETE http://localhost:8080/greeting/employees/47


// BAD
// http://localhost:8080/greeting/initial/business/ref/100
// http://localhost:8080/greeting/initial/busines/ref/48

@RestController
@RequestMapping("greeting")
public class GreetingController {

    @Autowired
    @Qualifier("greetingService1")  // specifies implementation
    private GreetingService greetingService;

    @Autowired
    @Qualifier("greetingService2")  // specifies implementation
    private GreetingService greetingJaker;

    private static final Logger logger = LoggerFactory.getLogger(GreetingController.class);

    @GetMapping(value = "/initial/{type}/ref/{id}")
    public ResponseEntity<?> getGreeting(@PathVariable("type") String classification, @PathVariable int id) {
        logger.info("greeting endpoint hit: classification: " + classification + "  id: " + id);
        loggingHierarchy();

        logger.info("IGNORE FROM HERE");
        logger.info(greetingJaker.getGreeting(48, "personal"));
        logger.info("FINISH IGNORING");

        return ResponseEntity.ok().body(greetingService.getGreeting(id, classification));
    }

    @GetMapping(value = "/initial/employees")
    public ResponseEntity<?> getEmployees() {
        return ResponseEntity.ok().body(greetingService.findAll());
    }


    @DeleteMapping(value = "/employees/{id}")
    public ResponseEntity<HttpStatus> removeEmployee(@PathVariable("id") int id)
    {
        logger.info("How's that!");

        //code
        //return new ResponseEntity<HttpStatus>(HttpStatus.ACCEPTED);
        return new ResponseEntity<HttpStatus>(HttpStatus.ACCEPTED);
    }



    // TRACE | DEBUG | INFO | WARN | ERROR
    // LOGGING: Logback is built on Apache Commons Logging
    // https://www.baeldung.com/spring-boot-logging
    private void loggingHierarchy() {
        logger.trace("A TRACE Message");  // TRACE | DEBUG | INFO | WARN | ERROR
        logger.debug("A DEBUG Message");  //         DEBUG | INFO | WARN | ERROR
        logger.info("An INFO Message");   //                 INFO | WARN | ERROR
        logger.warn("A WARN Message");    //                        WARN | ERROR
        logger.error("An ERROR Message"); //                               ERROR
    }

}
