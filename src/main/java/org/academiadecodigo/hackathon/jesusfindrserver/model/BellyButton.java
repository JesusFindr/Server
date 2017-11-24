package org.academiadecodigo.hackathon.jesusfindrserver.model;

/**
 * MIT License
 * (c) 2017 Ricardo Constantino
 */

public enum BellyButton {
    INNIE("Innie"),
    OUTTIE("Outtie"),
    OTHER("Other");

    private String type;

    BellyButton(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
