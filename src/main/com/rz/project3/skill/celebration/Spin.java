package com.rz.project3.skill.celebration;

import com.rz.project3.skill.combat.Combat;
import com.rz.project3.treasure.Armor;
import com.rz.project3.treasure.Gem;
import com.rz.project3.treasure.Sword;

public class Spin extends Celebrate{
    public Spin(Combat decoratedCombat) {
        this.decoratedCombat = decoratedCombat;
    }

    @Override
    public int combat(Armor armor, Gem gem, Sword sword, StringBuilder builder) {
        int result = decoratedCombat.combat(armor, gem, sword, builder);
        if(result == 0) {
            builder.append("spin, ");
        }
        return result;
    }
}
