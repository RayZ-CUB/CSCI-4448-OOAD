package com.rz.adventurer;

import com.rz.Constants;

public class AdventurerFactory {
    public static Adventurer getAdventurer(String name, String type) {
        return switch (type) {
            case Constants.BRAWLER_NAME -> new Brawler(name);
            case Constants.RUNNER_NAME -> new Runner(name);
            case Constants.SNEAKER_NAME -> new Sneaker(name);
            case Constants.THIEF_NAME -> new Thief(name);
            default -> null;
        };
    }
}
