package com.rz.project2.adventurer;

public class Brawler extends Adventurer {
    public Brawler() {
        this.setName("B");
    }
    @Override
    public int attack() {
        return attack() + 2;
    }
}
