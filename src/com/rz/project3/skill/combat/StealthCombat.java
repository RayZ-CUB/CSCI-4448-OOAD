package com.rz.project3.skill.combat;

import com.rz.project3.util.GameUtil;

public class StealthCombat implements Combat{
    @Override
    public int combat() {
        return GameUtil.rollDices();
    }
}
