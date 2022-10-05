package com.rz.project3.skill.combat;

import com.rz.project3.treasure.Armor;
import com.rz.project3.treasure.Gem;
import com.rz.project3.treasure.Sword;
import com.rz.project3.util.GameUtil;

public class ExpertCombat extends Combat{

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
