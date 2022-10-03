package com.rz.project3.skill.search;

import com.rz.project3.util.GameUtil;

public class CarefulSearch implements Search{
    @Override
    public boolean search() {
        if (GameUtil.rollDices() > 7) {
            return true;
        }
        return false;
    }
}
