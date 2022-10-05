package com.rz.project3.adventurer;

import com.rz.project3.skill.combat.StealthCombat;
import com.rz.project3.skill.search.QuickSearch;

public class Sneaker extends Adventurer {

    public Sneaker(String sneakerName, String sneakerFullName, StealthCombat stealthCombat, QuickSearch quickSearch) {
        super(sneakerName, sneakerFullName, stealthCombat, quickSearch);
    }
}
