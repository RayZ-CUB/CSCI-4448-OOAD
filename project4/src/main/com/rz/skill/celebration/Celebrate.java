package com.rz.skill.celebration;

import com.rz.skill.combat.Combat;
import com.rz.treasure.Armor;
import com.rz.treasure.Gem;
import com.rz.treasure.Sword;

//use Decorator pattern
public abstract class Celebrate extends Combat {
    Combat decoratedCombat;

    @Override
    public abstract int combat(Armor armor, Gem gem, Sword sword, StringBuilder builder);
}
