package com.example.springboot.dummy.SpringBootSpringWebStarter.service;

import org.springframework.stereotype.Service;

@Service("greetingJoker") // name is not necessary if it matches the component bean name
public class GreetingJoker implements Greeter {

    @Override
    public String getGreeting(int id, String type) {
        return "GREETING JOKER :)";
    }

}
