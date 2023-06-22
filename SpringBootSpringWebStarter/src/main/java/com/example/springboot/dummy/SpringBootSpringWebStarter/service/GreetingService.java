package com.example.springboot.dummy.SpringBootSpringWebStarter.service;

import com.example.springboot.dummy.SpringBootSpringWebStarter.dm.Employee;

import java.util.List;

public interface GreetingService {
    String getGreeting(int id, String type);

    List<Employee> findAll();

}
