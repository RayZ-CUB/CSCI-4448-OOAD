package com.rz.skill.combat;

import com.rz.treasure.Armor;
import com.rz.treasure.Gem;
import com.rz.treasure.Sword;
import com.rz.util.GameUtil;

public class ExpertCombat extends Combat {

    @Override
    public int combat(Armor armor, Gem gem, Sword sword, StringBuilder builder) {
        int adventurerAttack = GameUtil.rollDices() + 2;
        int creatureAttack = GameUtil.rollDices();

        if (armor != null) {
            creatureAttack--;
        }

        if (gem != null) {
            creatureAttack++;
        }

        if (sword != null) {
            adventurerAttack++;
        }

        if (adventurerAttack > creatureAttack) {
            return 0;
        } else if (adventurerAttack < creatureAttack) {
            return 1;
        } else {
            return 2;
        }
    }
}
