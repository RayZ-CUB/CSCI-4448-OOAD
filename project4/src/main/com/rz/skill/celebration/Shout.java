package com.rz.skill.celebration;

import com.rz.skill.combat.Combat;
import com.rz.treasure.Armor;
import com.rz.treasure.Gem;
import com.rz.treasure.Sword;

public class Shout extends Celebrate {
    public Shout(Combat decoratedCombat) {
        this.decoratedCombat = decoratedCombat;
    }

    @Override
    public int combat(Armor armor, Gem gem, Sword sword, StringBuilder builder) {
        int result = decoratedCombat.combat(armor, gem, sword, builder);
        if(result == 0) {
            builder.append("shout, ");
        }
        return result;
    }

}
