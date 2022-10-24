package com.rz.adventurer;

import com.rz.Constants;
import com.rz.skill.combat.ExpertCombat;
import com.rz.skill.search.CarelessSearch;

public class Brawler extends Adventurer {
    public Brawler(String name) {
        super(name, Constants.BRAWLER_NAME, 12, new ExpertCombat(), new CarelessSearch());
    }
}
