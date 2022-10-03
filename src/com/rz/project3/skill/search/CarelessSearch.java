package com.rz.project3.skill.search;

import java.util.Random;

public class CarelessSearch implements Search {
    @Override
    public boolean search() {
        Random random = new Random();
        int result = random.nextInt(3);
        if (result == 0 || rollDices() <= 9) {
            return false;
        }

        return true;
    }

    private int rollDices() {
        Random random = new Random();
        return random.nextInt(6) + 1 + random.nextInt(6) + 1;
    }
}
