package com.example.springboot.dummy.SpringBootSpringWebStarter.ctrl;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.springboot.dummy.SpringBootSpringWebStarter.service.GreetingService;
import com.example.springboot.dummy.SpringBootSpringWebStarter.service.GreetingService1;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

/*
Tests only the web layer (ie it does not start the server)
 */

@WebMvcTest(GreetingController.class)
public class GreetingControllerWebLayerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    @Qualifier("greetingService1")
    private GreetingService greetingService;

    @MockBean
    @Qualifier("greetingService2")
    private GreetingService greetingJaker;


    @Test
    public void greetingShouldReturnMessageFromService() throws Exception {
        int id = 48;

        when(greetingJaker.getGreeting(48, "personal")).thenReturn("GOODDAY Jose! - id = " + id);
        when(greetingService.getGreeting(Mockito.anyInt(), Mockito.anyString())).thenReturn("GOODDAY Jose! - id = " + id);

        this.mockMvc.perform(get("http://localhost:8080/greeting/initial/personal/ref/" + 1)).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("GOODDAY Jose!")));
    }
}

