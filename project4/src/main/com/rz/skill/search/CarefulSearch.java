package com.rz.skill.search;

import com.rz.util.GameUtil;

public class CarefulSearch implements Search {
    @Override
    public boolean search() {
        if (GameUtil.rollDices() > 4) {
            return true;
        }
        return false;
    }
}
