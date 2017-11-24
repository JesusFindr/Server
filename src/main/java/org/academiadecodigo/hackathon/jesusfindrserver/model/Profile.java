package org.academiadecodigo.hackathon.jesusfindrserver.model;

/**
 * MIT License
 * (c) 2017 Ricardo Constantino
 */

public class Profile {
    private User user;
    private int age;
    private SexType sexType;
    private int image;
    private ShoeSize shoeSize;
    private BellyButton bellyButton;
    private String spiritAnimal;
    private BrowsType browsType;
    private boolean backHair;

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

    public void setBackHair(boolean backHair) {
        this.backHair = backHair;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSexType(SexType sexType) {
        this.sexType = sexType;
    }

    public void setImage(int image) {
        this.image = image;
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

    public boolean getBackHair() {
        return backHair;
    }

    public int getAge() {
        return age;
    }

    public SexType getSexType() {
        return sexType;
    }

    public int getImage() {
        return image;
    }

    public User getUser() {
        return user;
    }
}
