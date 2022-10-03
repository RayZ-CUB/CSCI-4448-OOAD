package com.rz.project3.skill.search;

import java.util.Random;

public class CarefulSearch implements Search{
    @Override
    public boolean search() {
        if (rollDices() > 7) {
            return true;
        }
        return false;
    }

    private int rollDices() {
        Random random = new Random();
        return random.nextInt(6) + 1 + random.nextInt(6) + 1;
    }
}
