package org.academiadecodigo.hackathon.jesusfindrserver.model;

/**
 * MIT License
 * (c) 2017 Ricardo Constantino
 */

public class Profile {
    private User user;
    private String realName;
    private int age;
    private SexType sexType;
    private int image;
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

    public void setRealName(String realName) {
        this.realName = realName;
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

    public int getFingersInHands() {
        return fingersInHands;
    }

    public String getRealName() {
        return realName;
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
}
