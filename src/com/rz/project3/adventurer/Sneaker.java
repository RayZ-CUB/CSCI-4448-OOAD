package com.rz.project3.adventurer;

import com.rz.project3.skill.combat.StealthCombat;
import com.rz.project3.skill.search.QuickSearch;

//public class Sneaker extends Adventurer {
//    public final Combat combat = new StealthCombat();
//    public final Search search = new QuickSearch();
//
//    public Sneaker() {
//        this.setName("S");
//    }
//}

public class Sneaker extends Adventurer{

    public Sneaker(String sneakerName, StealthCombat stealthCombat, QuickSearch quickSearch) {
        super(sneakerName, stealthCombat, quickSearch);
    }
}
