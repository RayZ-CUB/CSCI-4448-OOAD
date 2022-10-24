package com.rz.skill.search;

import com.rz.util.GameUtil;

public class CarelessSearch implements Search {
    @Override
    public boolean search() {
        if (GameUtil.rollDices() > 7) {
            return true;
        }
        return false;
    }
}
