package com.example.springboot.dummy.SpringBootSpringWebStarter;
import com.example.springboot.dummy.SpringBootSpringWebStarter.ctrl.GreetingController;
import org.springframework.beans.factory.annotation.Autowired;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SmokeTest {

    @Autowired
    private GreetingController controller;


    @Test
    public void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }

}



