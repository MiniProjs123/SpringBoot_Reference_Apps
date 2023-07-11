package com.example.springboot.dummy.SpringBootSpringWebStarter.vo;
import com.example.springboot.dummy.SpringBootSpringWebStarter.dm.Club;

public class EmployeeVO {

    private int id;
    private String firstname;
    private String surname;
    private int age;
    private Club club;

    public EmployeeVO() {

    }

    public EmployeeVO(String firstname, String surname, int age) {
        this.firstname = firstname;
        this.surname = surname;
        this.age = age;
    }
    public EmployeeVO(String firstname, String surname, int age, Club club) {
        this(firstname, surname, age);
        this.club = club;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {
        return age;
    }


    // TODO stub only!
    public int getId() {
        return 1;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }
}
