package com.example.springboot.dummy.SpringBootSpringWebStarter.ctrl;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.example.springboot.dummy.SpringBootSpringWebStarter.dm.Employee;
import com.example.springboot.dummy.SpringBootSpringWebStarter.service.GreetingService1;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @MockBean
    GreetingService1 greetingService;

    @Autowired
    MockMvc mockMvc;


    @Test
    public void testfindAll() throws Exception {

        Employee employee1 = new Employee("Paul", "Jones", 44);
        Employee employee2 = new Employee("John", "Richards", 45);
        List<Employee> employees = Arrays.asList(employee1, employee2);

        Mockito.when(greetingService.findAll()).thenReturn(employees);

        mockMvc.perform(get("/greeting/initial/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(2)))
                .andExpect(jsonPath("$[0].firstname", Matchers.is("Paul")))
                .andExpect(jsonPath("$[0].surname", Matchers.is("Jones")))
                .andExpect(jsonPath("$[0].age", Matchers.is(44)))
                .andExpect(jsonPath("$[1].firstname", Matchers.is("John")))
                .andExpect(jsonPath("$[1].surname", Matchers.is("Richards")))
                .andExpect(jsonPath("$[1].age", Matchers.is(45)));
    }

}
