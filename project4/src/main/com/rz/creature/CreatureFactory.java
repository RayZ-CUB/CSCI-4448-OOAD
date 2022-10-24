package com.rz.creature;

import com.rz.Constants;

public class CreatureFactory {
    public static Creature getCreature(int id, String type) {
        return switch (type) {
            case Constants.BLINKER_FULL_NAME -> new Blinker(id);
            case Constants.ORBITER_FULL_NAME -> new Orbiter(id);
            case Constants.SEEKER_FULL_NAME -> new Seeker(id);
            default -> null;
        };
    }
}
