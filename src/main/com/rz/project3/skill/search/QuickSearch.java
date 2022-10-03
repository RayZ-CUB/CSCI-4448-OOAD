package com.rz.project3.skill.search;

import com.rz.project3.util.GameUtil;

public class QuickSearch implements Search{
    @Override
    public boolean search() {
        if (GameUtil.rollDices() > 10) {
            return true;
        }
        return false;
    }
}
