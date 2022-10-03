package com.rz.project3.skill.combat;

import com.rz.project3.util.GameUtil;

public class ExpertCombat implements Combat{
    @Override
    public int combat() {
        return GameUtil.rollDices() + 2;
    }
}
