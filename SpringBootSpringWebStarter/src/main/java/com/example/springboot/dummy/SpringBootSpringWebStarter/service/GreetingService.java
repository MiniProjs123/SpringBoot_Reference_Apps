package com.example.springboot.dummy.SpringBootSpringWebStarter.service;

import com.example.springboot.dummy.SpringBootSpringWebStarter.dm.Employee;
import com.example.springboot.dummy.SpringBootSpringWebStarter.vo.EmployeeVO;

import java.util.List;

public interface GreetingService {
    String getGreeting(int id, String type);

    List<Employee> findAll();

    EmployeeVO addNewEmployee(String firstname, String surname, int age);

    EmployeeVO updateEmployee(int id, EmployeeVO updated);

}
