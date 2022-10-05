package com.rz.project3.adventurer;

import com.rz.project3.skill.combat.ExpertCombat;
import com.rz.project3.skill.search.CarelessSearch;

public class Brawler extends Adventurer {
    public Brawler(String brawlerName, String brawlerFullName, ExpertCombat expertCombat, CarelessSearch carelessSearch) {
        super(brawlerName, brawlerFullName, expertCombat, carelessSearch);
    }
}
