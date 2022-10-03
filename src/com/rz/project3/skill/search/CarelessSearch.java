package com.rz.project3.skill.search;

import com.rz.project3.util.GameUtil;

import java.util.Random;

public class CarelessSearch implements Search {
    @Override
    public boolean search() {
        Random random = new Random();
        int result = random.nextInt(3);
        if (result == 0 || GameUtil.rollDices() <= 9) {
            return false;
        }

        return true;
    }
}
