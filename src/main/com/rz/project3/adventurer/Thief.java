package com.rz.project3.adventurer;

import com.rz.project3.skill.combat.TrainedCombat;
import com.rz.project3.skill.search.CarefulSearch;

public class Thief extends Adventurer {
    public Thief(String thiefName, String thiefFullName, TrainedCombat trainedCombat, CarefulSearch carefulSearch) {
        super(thiefName, thiefFullName, trainedCombat, carefulSearch);
    }
}
