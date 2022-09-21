package com.rz.project2.adventurer;

public class Thief extends Adventurer{
    public Thief() {
        this.setName("T");
    }

    @Override
    public int attack() {
        return rollDices() + 1;
    }
}
