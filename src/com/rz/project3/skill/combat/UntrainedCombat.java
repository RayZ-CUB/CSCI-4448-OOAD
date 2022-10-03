package com.rz.project3.skill.combat;

import com.rz.project3.util.GameUtil;

public class UntrainedCombat implements Combat{
    @Override
    public int combat() {
        return GameUtil.rollDices();
    }
}
