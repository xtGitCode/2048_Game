package com.example.demo;

public class User {
    private String password;
    private long highScore;

    //Constructors
    public User(String password, long highScore) {
        this.password = password;
        this.highScore = highScore;
    }

    //getter setter
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getHighScore() {
        return highScore;
    }

    public void setHighScore(long highScore) {
        this.highScore = highScore;
    }
}
