package org.academiadecodigo.hackathon.jesusfindrserver.model;

/**
 * MIT License
 * (c) 2017 Ricardo Constantino
 */

public class Profile {
    private User user;
    private ShoeSize shoeSize;
    private BellyButton bellyButton;
    private String spiritAnimal;
    private BrowsType browsType;
    private int fingersInHands;

    public Profile(User user) {
        this.user = user;
    }

    public void setShoeSize(ShoeSize shoeSize) {
        this.shoeSize = shoeSize;
    }

    public void setBellyButton(BellyButton bellyButton) {
        this.bellyButton = bellyButton;
    }

    public void setSpiritAnimal(String spiritAnimal) {
        this.spiritAnimal = spiritAnimal;
    }

    public void setBrowsType(BrowsType browsType) {
        this.browsType = browsType;
    }

    public void setFingersInHands(int fingersInHands) {
        this.fingersInHands = fingersInHands;
    }

    public ShoeSize getShoeSize() {
        return shoeSize;
    }

    public BellyButton getBellyButton() {
        return bellyButton;
    }

    public String getSpiritAnimal() {
        return spiritAnimal;
    }

    public BrowsType getBrowsType() {
        return browsType;
    }

    public int getFingersInHands() {
        return fingersInHands;
    }
}
