package com.rz.project3.adventurer;

import com.rz.project3.skill.combat.UntrainedCombat;
import com.rz.project3.skill.search.QuickSearch;

public class Runner extends Adventurer {
    public Runner(String runnerName, String runnerFullName, UntrainedCombat untrainedCombat, QuickSearch quickSearch) {
        super(runnerName, runnerFullName, untrainedCombat, quickSearch);
    }
}
