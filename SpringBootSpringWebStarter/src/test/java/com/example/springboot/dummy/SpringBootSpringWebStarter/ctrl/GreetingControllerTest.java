package com.example.springboot.dummy.SpringBootSpringWebStarter.ctrl;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.example.springboot.dummy.SpringBootSpringWebStarter.dm.*;
import com.example.springboot.dummy.SpringBootSpringWebStarter.vo.EmployeeVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.springboot.dummy.SpringBootSpringWebStarter.ex.BadIdeaException;
import com.example.springboot.dummy.SpringBootSpringWebStarter.service.GreetingService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
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

    @BeforeAll
    public static void doOnceBeforeAnyTestsRun() {
        logger.info("Runs once before log file is booted ..... see this message on the IntelliJ output only");
    }

    @BeforeEach
    public void doBeforeEachTest() {
        logger.info("Running before each test.....");
    }

    @Test
    public void testFindAll() throws Exception {
        Employee employee1 = new Employee("Paul", "Jones", 44);
        Employee employee2 = new Employee("John", "Richards", 45);
        Club burley = new Club("Burley", 1887, true);

        Employee s1 = new Employee("Sally", "Sandals", 47, burley);
        List<Employee> employees = Arrays.asList(employee1, employee2, s1);
        List<Match> matches = Arrays.asList(new Match(MatchType.OVERS_60, "Moordown"),
                                            new Match(MatchType.OVERS_50, "New Milton"));
        burley.setMatches(matches);

        Mockito.when(greetingService.findAll()).thenReturn(employees);

        mockMvc.perform(get("/greeting/initial/employees").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
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
                .andExpect(jsonPath("$[2].club.established", Matchers.is(1887)))
                .andExpect(jsonPath("$[2].club.stillOperational", Matchers.is(true)))

                .andExpect(jsonPath("$[2].club.matches[0].type", Matchers.is("OVERS_60")))
                .andExpect(jsonPath("$[2].club.matches[0].opposition", Matchers.is("Moordown")))
                .andExpect(jsonPath("$[2].club.matches[1].type", Matchers.is("OVERS_50")))
                .andExpect(jsonPath("$[2].club.matches[1].opposition", Matchers.is("New Milton")));
    }

    @Test
    public void findAllGoneWrong() throws Exception {
        Mockito.when(greetingService.findAll()).thenThrow(new BadIdeaException("Caused some error"));

        mockMvc.perform(get("/greeting/initial/employees").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testFindOne() throws Exception {
        int id = 48;

        when(greetingJaker.getGreeting(48, "personal")).thenReturn("GOODDAY Jose! - id = " + id);
        when(greetingService.getGreeting(Mockito.anyInt(), Mockito.anyString())).thenReturn("GOODDAY Jose! - id = " + id);

        mockMvc.perform(get("/greeting/initial/personal/ref/" + 1)).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("GOODDAY Jose!")));
    }

    @Test
    @DisplayName("Test adding a new employee - but without the created employee returned")
    public void testCreateNewEmployee() throws Exception {
        logger.info("testing a post endpoint");

        when(greetingService.addNewEmployee(Mockito.anyString(), Mockito.anyString(), Mockito.anyInt())).
                thenReturn(new EmployeeVO("Big", "Ben", 39));

        String jsonContent = asJsonString(new EmployeeVO("firstName4", "lastName4", 45));

        mockMvc.perform( MockMvcRequestBuilders
                        .post("/greeting/employees/add1")
                        .content(jsonContent)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Test adding a new employee - and the created employee is returned")
    public void testCreateNewEmployeeWithEmployeeObjectReturned() throws Exception {
        logger.info("testing a post endpoint");

        Club burley = new Club("Burley", 1887, true);
        EmployeeVO mockedObject = new EmployeeVO("Big", "Ben", 39, burley);

        when(greetingService.addNewEmployee(Mockito.anyString(), Mockito.anyString(), Mockito.anyInt())).
                thenReturn(mockedObject);

        String jsonContent = asJsonString(new EmployeeVO("firstName4", "lastName4", 45));

        mockMvc.perform( MockMvcRequestBuilders
                        .post("/greeting/employees/add2")
                        .content(jsonContent)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isCreated())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(MockMvcResultMatchers.jsonPath("$.age", Matchers.is(39)))
               .andExpect(MockMvcResultMatchers.jsonPath("$.firstname", Matchers.is("Big")))
               .andExpect(MockMvcResultMatchers.jsonPath("$.surname", Matchers.is("Ben")))
               .andExpect(jsonPath("$.club.name", Matchers.is("Burley")))
               .andExpect(jsonPath("$.club.established", Matchers.is(1887)))
               .andExpect(jsonPath("$.club.stillOperational", Matchers.is(true)));
    }

    @Test
    @DisplayName("Test adding when mocked employee returns null")
    public void testCreateNewEmployeeWithNullEmployeeMock() throws Exception {
        when(greetingService.addNewEmployee(Mockito.anyString(), Mockito.anyString(), Mockito.anyInt())).
                thenReturn(null);

        String jsonContent = asJsonString(new EmployeeVO("firstName4", "lastName4", 45));
        mockMvc.perform( MockMvcRequestBuilders
                        .post("/greeting/employees/add2")
                        .content(jsonContent)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }


    @Test
    @DisplayName("Adding a new object - POC")
    public void testCreateNewObject() throws Exception {
        String jsonContent = asJsonString(new POC("SomeObject"));
        mockMvc.perform( MockMvcRequestBuilders
                        .post("/greeting/poc/add")
                        .content(jsonContent)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.field1", Matchers.is("SomeObject")));
    }

    @Test
    @DisplayName("Test delete object")
    public void deleteEmployeeAPI() throws Exception
    {
        mockMvc.perform( MockMvcRequestBuilders.delete("/greeting/employee/{id}", 1) )
                .andExpect(status().isAccepted());
    }

    @Test
    @DisplayName("Test update object")
    public void updateEmployeeAPI() throws Exception
    {
        EmployeeVO updatedEmpl = new EmployeeVO("firstName2", "lastName2", 45);
        updatedEmpl.setId(1);
        String jsonContent = asJsonString(updatedEmpl);

        // the actual mocked object to be returned
        EmployeeVO mockedObject = new EmployeeVO("John", "Wisden", 50);

        when(greetingService.updateEmployee(Mockito.anyInt(), Mockito.any())).
                thenReturn(mockedObject);

        mockMvc.perform( MockMvcRequestBuilders
                        .put("/greeting/employee/{id}", 1)
                        .content(jsonContent)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstname").value("John"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.surname").value("Wisden"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.age").value(50));
    }

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            System.out.println(jsonContent);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
