package com.example.springboot.dummy.SpringBootSpringWebStarter.dm;

import java.util.List;

public class Club {

    private String name;
    private int established;
    private boolean stillOperational;

    private List<Match> matches;

    public Club(String name, int established, boolean stillOperational) {
        this.name = name;
        this.established = established;
        this.stillOperational = stillOperational;
    }

    public Club(String name, int established, boolean stillOperational, List<Match> matches) {
        this(name, established, stillOperational);
        this.matches = matches;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEstablished() {
        return established;
    }

    public void setEstablished(int established) {
        this.established = established;
    }

    public boolean isStillOperational() {
        return stillOperational;
    }

    public void setStillOperational(boolean stillOperational) {
        this.stillOperational = stillOperational;
    }

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }
}
