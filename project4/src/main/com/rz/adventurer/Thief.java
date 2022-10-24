package com.rz.adventurer;

import com.rz.Constants;
import com.rz.skill.combat.TrainedCombat;
import com.rz.skill.search.CarefulSearch;

public class Thief extends Adventurer {
    public Thief(String name) {
        super(name, Constants.THIEF_NAME, 10, new TrainedCombat(), new CarefulSearch());
    }
}
