package com.rz.skill.combat;

import com.rz.treasure.Armor;
import com.rz.treasure.Gem;
import com.rz.treasure.Sword;

import static com.rz.skill.combat.UntrainedCombat.defaultCombat;


public class StealthCombat extends Combat {
    @Override
    public int combat(Armor armor, Gem gem, Sword sword, StringBuilder builder) {
        return defaultCombat(armor,gem,sword);
    }
}
