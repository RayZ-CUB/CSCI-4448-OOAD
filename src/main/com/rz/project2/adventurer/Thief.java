package com.rz.project2.adventurer;


//Using Inheritance, ass a child class inheriting adventurer class
public class Thief extends Adventurer {
    public Thief() {
        this.setName("T");
    }

    @Override
    public int attack() {
        return rollDices() + 1;
    }

    @Override
    //Using polymorphism in here, methods overriding
    public boolean searchTreasure() {
        if ((rollDices() + 1) >= 10) {
            setTreasureCount(getTreasureCount() + 1);
            return true;
        }
        return false;
    }
}
