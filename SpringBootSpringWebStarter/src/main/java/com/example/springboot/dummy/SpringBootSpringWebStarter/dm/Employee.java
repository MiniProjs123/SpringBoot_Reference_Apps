package com.example.springboot.dummy.SpringBootSpringWebStarter.dm;

public class Employee {

    private String firstname;
    private String surname;
    private int age;
    private Club club;


    public Employee(String firstname, String surname, int age) {
        this.firstname = firstname;
        this.surname = surname;
        this.age = age;
    }
    public Employee(String firstname, String surname, int age, Club club) {
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
