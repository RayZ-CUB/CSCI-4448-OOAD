package com.rz.skill.search;

import com.rz.util.GameUtil;

public class QuickSearch implements Search {
    @Override
    public boolean search() {
        if (GameUtil.rollDices() > 6) {
            return true;
        }
        return false;
    }
}
