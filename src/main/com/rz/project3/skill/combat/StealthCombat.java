package com.rz.project3.skill.combat;

import com.rz.project3.treasure.Armor;
import com.rz.project3.treasure.Gem;
import com.rz.project3.treasure.Sword;

import static com.rz.project3.skill.combat.UntrainedCombat.defaultCombat;

public class StealthCombat extends Combat{
    @Override
    public int combat(Armor armor, Gem gem, Sword sword, StringBuilder builder) {
        return defaultCombat(armor, gem, sword);
    }
}
