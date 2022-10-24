package com.rz.adventurer;

import com.rz.Constants;
import com.rz.skill.combat.UntrainedCombat;
import com.rz.skill.search.QuickSearch;

public class Runner extends Adventurer {
    public Runner(String name) {
        super(name, Constants.RUNNER_NAME, 10, new UntrainedCombat(), new QuickSearch());
    }
}
