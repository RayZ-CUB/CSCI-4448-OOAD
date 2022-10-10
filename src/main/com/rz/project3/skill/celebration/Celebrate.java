package com.rz.project3.skill.celebration;

import com.rz.project3.skill.combat.Combat;
import com.rz.project3.treasure.Armor;
import com.rz.project3.treasure.Gem;
import com.rz.project3.treasure.Sword;

//use Decorator pattern
public abstract class Celebrate extends Combat{
    Combat decoratedCombat;

    @Override
    public abstract int combat(Armor armor, Gem gem, Sword sword, StringBuilder builder);
}
