package com.rz.project2.adventurer;

public class Thief extends Adventurer{
    public Thief() {
        this.setName("T");
    }

    @Override
    public int attack() {
        return attack() + 1;
    }
    public int search() {
        return search() + 1;
    }
}
