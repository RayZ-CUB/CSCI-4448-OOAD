package com.rz.project2.adventurer;

public class Thief extends Adventurer {
    public Thief() {
        this.setName("T");
    }

    @Override
    public int attack() {
        return rollDices() + 1;
    }

    @Override
    public boolean searchTreasure() {
        if ((rollDices() + 1) >= 10) {
            setTreasureCount(getTreasureCount() + 1);
            return true;
        }
        return false;
    }
}
