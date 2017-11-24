package org.academiadecodigo.hackathon.jesusfindrserver.model;

/**
 * MIT License
 * (c) 2017 Ricardo Constantino
 */

public enum SexType {
    VIRGIN("Saint"),
    ONCE_PER_YEAR("Once a year"),
    ONCE_PER_WEEK("Once a week"),
    ONCE_PER_DAY("Once a day"),
    ALL_HACKATHON_LONG("All hackathon long!");

    private String type;

    SexType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
