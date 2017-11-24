package org.academiadecodigo.hackathon.jesusfindrserver.model;

/**
 * MIT License
 * (c) 2017 Ricardo Constantino
 */

public enum ShoeSize {
    SMALL("Small"),
    MEDIUM("Medium"),
    BIG("Big");

    private String type;

    ShoeSize(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
