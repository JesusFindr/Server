package org.academiadecodigo.hackathon.jesusfindrserver.model;

/**
 * MIT License
 * (c) 2017 Ricardo Constantino
 */

public class User {

    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}
