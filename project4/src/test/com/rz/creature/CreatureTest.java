package com.rz.creature;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreatureTest {

    @Test
    void currentRoomNumber_validCoordinate_sameRoomNumber() {
        Creature creature = new Blinker(0);
        int[] coordinate = creature.getCoordinate();
        String currentRoomNumber = creature.currentRoomNumber();
        String[] splitted = currentRoomNumber.split("-");

        assertEquals(coordinate[0], Integer.valueOf(splitted[0]));
        assertEquals(coordinate[1], Integer.valueOf(splitted[1]));
        assertEquals(coordinate[2], Integer.valueOf(splitted[2]));
    }
}