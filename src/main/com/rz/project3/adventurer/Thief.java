package com.rz.project3.adventurer;

import com.rz.project3.skill.combat.TrainedCombat;
import com.rz.project3.skill.search.CarefulSearch;
//Using Inheritance, ass a child class inheriting adventurer class
//public class Thief extends Adventurer {
//    public final Combat combat = new TrainedCombat();
//    public final Search search = new CarefulSearch();
//
//    public Thief() {
//        this.setName("T");
//    }
//}



public class Thief extends Adventurer{
    public Thief(String thiefName, TrainedCombat trainedCombat, CarefulSearch carefulSearch) {
        super(thiefName, trainedCombat, carefulSearch);
    }
}
