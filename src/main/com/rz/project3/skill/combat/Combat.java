package com.rz.project3.skill.combat;

import com.rz.project3.treasure.Armor;
import com.rz.project3.treasure.Gem;
import com.rz.project3.treasure.Sword;


//Strategy pattern for Combat
public abstract class Combat {
    public abstract int combat(Armor armor, Gem gem, Sword sword, StringBuilder builder);
}
