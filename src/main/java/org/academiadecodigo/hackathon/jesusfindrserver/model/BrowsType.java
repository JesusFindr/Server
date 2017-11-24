package org.academiadecodigo.hackathon.jesusfindrserver.model;

/**
 * MIT License
 * (c) 2017 Ricardo Constantino
 */

public enum BrowsType {
    SPLIT("Split brows"),
    UNIBROW("Unibrow");

    private String type;

    BrowsType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
