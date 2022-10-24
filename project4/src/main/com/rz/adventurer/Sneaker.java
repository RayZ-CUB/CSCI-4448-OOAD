package com.rz.adventurer;

import com.rz.Constants;
import com.rz.skill.combat.StealthCombat;
import com.rz.skill.search.QuickSearch;

public class Sneaker extends Adventurer {

    public Sneaker(String name) {
        super(name, Constants.SNEAKER_NAME, 8, new StealthCombat(), new QuickSearch());
    }
}
