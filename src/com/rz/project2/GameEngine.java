package com.rz.project2;

import com.rz.project2.adventurer.Adventurer;
import com.rz.project2.adventurer.Brawler;

public class GameEngine {
    public static void main(String[] args) {
        Brawler brawler = new Brawler();
        System.out.println("HP = " + brawler.getHP());
        System.out.println("treasureCount = " + brawler.getTreasureCount());
        System.out.println("Room = " + brawler.getRoom());
    }
}
