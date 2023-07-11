package com.example.springboot.dummy.SpringBootSpringWebStarter.service;

import com.example.springboot.dummy.SpringBootSpringWebStarter.dm.Employee;
import com.example.springboot.dummy.SpringBootSpringWebStarter.vo.EmployeeVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("greetingService2") // name is not necessary if it matches the component bean name
public class GreetingService2 implements GreetingService {

    @Override
    public String getGreeting(int id, String type) {
        return "GREETING JOKER :)";
    }
    @Override
    public List<Employee> findAll() {
        return null;
    }

    public EmployeeVO addNewEmployee(String firstname, String surname, int age) {
        return null;
    }

}
