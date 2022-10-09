package com.rz.project3.creature;

import com.rz.project3.Constants;
import com.rz.project3.map.Room;

import java.util.Random;

public class Blinker extends Creature {

    public Blinker(int id) {
        this.fullName = Constants.BLINKER_FULL_NAME;
        this.setName("B");
        this.id = id;

        Random random = new Random();   // https://stackoverflow.com/questions/363681/how-do-i-generate-random-integers-within-a-specific-range-in-java
        int z = 4;
        int y = random.nextInt(3);
        int x = random.nextInt(3);
        getCoordinate()[0] = z;
        getCoordinate()[1] = y;
        getCoordinate()[2] = x;
    }

    @Override
    public void move() {
        Room currentRoom = getRoom();
        // At least one adventurer in current room
        if (!currentRoom.getAdventurers().isEmpty()) {
            return;
        }

        Random random = new Random();
        getCoordinate()[0] = random.nextInt(4)+1;
        getCoordinate()[1] = random.nextInt(3);
        getCoordinate()[2] = random.nextInt(3);
    }
}
