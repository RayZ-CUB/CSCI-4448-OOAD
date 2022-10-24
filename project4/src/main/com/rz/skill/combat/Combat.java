package com.rz.skill.combat;

import com.rz.treasure.Armor;
import com.rz.treasure.Gem;
import com.rz.treasure.Sword;


//Strategy pattern for Combat
public abstract class Combat {
    public abstract int combat(Armor armor, Gem gem, Sword sword, StringBuilder builder);
}
