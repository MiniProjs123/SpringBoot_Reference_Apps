package com.example.springboot.dummy.SpringBootSpringWebStarter.service;

import com.example.springboot.dummy.SpringBootSpringWebStarter.dm.Club;
import com.example.springboot.dummy.SpringBootSpringWebStarter.dm.Employee;
import com.example.springboot.dummy.SpringBootSpringWebStarter.dm.Match;
import com.example.springboot.dummy.SpringBootSpringWebStarter.dm.MatchType;
import com.example.springboot.dummy.SpringBootSpringWebStarter.ex.BadIdeaException;
import com.example.springboot.dummy.SpringBootSpringWebStarter.ex.NotImplementedException;
import com.example.springboot.dummy.SpringBootSpringWebStarter.vo.EmployeeVO;
import io.micrometer.common.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class GreetingService1 implements GreetingService {

    public static final String BUSINESS = "business";
    public static final String PERSONAL = "personal";
    protected static final List<String> VALID_CLASSIFICATIONS = Arrays.asList(PERSONAL, BUSINESS);

    private Logger logger = LoggerFactory.getLogger(GreetingService1.class);

    @Override
    public String getGreeting(int id, String type) {

        validateGreetingId(id);
        validateGreetingType(type);

        return "GOODDAY Jose! - id = " + id;
    }

    private void validateGreetingId(int id) {
        logger.info("validating greeting id....");
        if (id == 100) {
            throw new NotImplementedException("not built yet");
        }
        logger.info("no issues with classification!");
    }

    private void validateGreetingType(String type) {
        logger.info("validating greeting type....");
        boolean isValid =
                VALID_CLASSIFICATIONS.stream().filter(s->s.equals(type)).findFirst().isPresent();
        if (!isValid) {
            throw new BadIdeaException("Invalid greeting classification - type = " + type);
        }
        logger.info("id is fine - it is not 100!");
    }

    public List<Employee> findAll() {
        Club burley = new Club("Burley", 1907, true);
        List<Match> matches = Arrays.asList(new Match(MatchType.OVERS_60, "Moordown"),
                new Match(MatchType.OVERS_50, "New Milton"));
        burley.setMatches(matches);
        Employee s1 = new Employee("Sally", "Sandals", 47, burley);

        return Arrays.asList(new Employee("John", "Smith", 50),
                new Employee("Joan", "Davies", 51),
                s1);
    }

    public EmployeeVO addNewEmployee(String firstname, String surname, int age) {
        if (StringUtils.isBlank(firstname)) {
            return null;
        }

        EmployeeVO employee = new EmployeeVO(firstname, surname, age);

        //....create somewhere...

        return employee;
    }

    public EmployeeVO updateEmployee(int id, EmployeeVO updatedEmpl) {

        // save updated Employee with existing id to database

        EmployeeVO employee = new EmployeeVO(updatedEmpl.getFirstname(), updatedEmpl.getSurname(), updatedEmpl.getAge());

        return employee;
    }



}
