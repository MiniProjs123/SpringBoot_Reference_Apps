package com.example.springboot.dummy.SpringBootSpringWebStarter.dm;

public class Match {
    private MatchType type;

    private String opposition;

    public Match(MatchType type, String opposition) {
        this.type = type;
        this.opposition = opposition;
    }

    public MatchType getType() {
        return type;
    }

    public void setType(MatchType type) {
        this.type = type;
    }

    public String getOpposition() {
        return opposition;
    }

    public void setOpposition(String opposition) {
        this.opposition = opposition;
    }
}
