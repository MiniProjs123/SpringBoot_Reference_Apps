package com.example.springboot.dummy.SpringBootSpringWebStarter.ctrl;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.springboot.dummy.SpringBootSpringWebStarter.dm.Club;
import com.example.springboot.dummy.SpringBootSpringWebStarter.dm.Employee;
import com.example.springboot.dummy.SpringBootSpringWebStarter.dm.Match;
import com.example.springboot.dummy.SpringBootSpringWebStarter.dm.MatchType;
import com.example.springboot.dummy.SpringBootSpringWebStarter.service.GreetingService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Arrays;
import java.util.List;

/*
This testing.txt approach does not start the server at all but to test only the layer below that,
where Spring handles the incoming HTTP request and hands it off to your controller.

Using MockMvc, almost all of the full stack is used, and your code will be called in exactly the same way
as if it were processing a real HTTP request but without the cost of starting the server.
- ie. the full Spring application context is started but without the server
 */

@SpringBootTest
@AutoConfigureMockMvc
public class GreetingControllerTest {

    private static final Logger logger = LoggerFactory.getLogger(GreetingControllerTest.class);

    @MockBean(name = "greetingService1")
    private GreetingService greetingService;

    @MockBean(name = "greetingService2")
    private GreetingService greetingJaker;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testFindAll() throws Exception {

        Employee employee1 = new Employee("Paul", "Jones", 44);
        Employee employee2 = new Employee("John", "Richards", 45);
        Club burley = new Club("Burley", 1907, true);

        Employee s1 = new Employee("Sally", "Sandals", 47, burley);
        List<Employee> employees = Arrays.asList(employee1, employee2, s1);
        List<Match> matches = Arrays.asList(new Match(MatchType.OVERS_60, "Moordown"),
                                            new Match(MatchType.OVERS_50, "New Milton"));
        burley.setMatches(matches);

        Mockito.when(greetingService.findAll()).thenReturn(employees);

        mockMvc.perform(get("/greeting/initial/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(3)))
                .andExpect(jsonPath("$[0].firstname", Matchers.is("Paul")))
                .andExpect(jsonPath("$[0].surname", Matchers.is("Jones")))
                .andExpect(jsonPath("$[0].age", Matchers.is(44)))
                .andExpect(jsonPath("$[0].club", Matchers.blankOrNullString()))

                .andExpect(jsonPath("$[1].firstname", Matchers.is("John")))
                .andExpect(jsonPath("$[1].surname", Matchers.is("Richards")))
                .andExpect(jsonPath("$[1].age", Matchers.is(45)))
                .andExpect(jsonPath("$[1].club", Matchers.blankOrNullString()))

                .andExpect(jsonPath("$[2].firstname", Matchers.is("Sally")))
                .andExpect(jsonPath("$[2].surname", Matchers.is("Sandals")))
                .andExpect(jsonPath("$[2].age", Matchers.is(47)))
                .andExpect(jsonPath("$[2].club.name", Matchers.is("Burley")))
                .andExpect(jsonPath("$[2].club.established", Matchers.is(1907)))
                .andExpect(jsonPath("$[2].club.stillOperational", Matchers.is(true)))

                .andExpect(jsonPath("$[2].club.matches[0].type", Matchers.is("OVERS_60")))
                .andExpect(jsonPath("$[2].club.matches[0].opposition", Matchers.is("Moordown")))
                .andExpect(jsonPath("$[2].club.matches[1].type", Matchers.is("OVERS_50")))
                .andExpect(jsonPath("$[2].club.matches[1].opposition", Matchers.is("New Milton")));
    }

    @Test
    public void testFindOne() throws Exception {
        int id = 48;

        when(greetingJaker.getGreeting(48, "personal")).thenReturn("GOODDAY Jose! - id = " + id);
        when(greetingService.getGreeting(Mockito.anyInt(), Mockito.anyString())).thenReturn("GOODDAY Jose! - id = " + id);

        mockMvc.perform(get("http://localhost:8080/greeting/initial/personal/ref/" + 1)).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("GOODDAY Jose!")));
    }

    @Test
    public void testPost() {
            logger.info("testPost() test stub is empty - used for mvn cmd check only");
    }

}
