package com.rz.project2.creature;

import com.rz.project2.Constants;
import com.rz.project2.map.Room;

import java.util.Random;

public class Orbiter extends Creature {
    private final String direction;

    public Orbiter() {
        // Set up name
        this.setName(Constants.ORBITER_NAME);

        // Set up starting room
        Random random = new Random();   // https://stackoverflow.com/questions/363681/how-do-i-generate-random-integers-within-a-specific-range-in-java
        int z = random.nextInt(4) + 1;
        int y = random.nextInt(3);
        int x = random.nextInt(3);
        while (x == 1 && y == 1) {
            y = random.nextInt(3);
            x = random.nextInt(3);
        }
        getCoordinate()[0] = z;
        getCoordinate()[1] = y;
        getCoordinate()[2] = x;

        // Set up movement direction
        if (random.nextBoolean()) {
            direction = Constants.CLOCKWISE;
        } else {
            direction = Constants.COUNTERCLOCKWISE;
        }
    }

    @Override
    public void move() {
        Room currentRoom = getRoom();
        // At least one adventurer in current room
        if (!currentRoom.getAdventurers().isEmpty()) {
            return;
        }
        
        if (direction.equals(Constants.CLOCKWISE)) {
            if (getCoordinate()[1] == 0 && getCoordinate()[2] < 2) {
                getCoordinate()[2] = getCoordinate()[2] + 1;
            } else if (getCoordinate()[1] < 2 && getCoordinate()[2] == 2) {
                getCoordinate()[1] = getCoordinate()[1] + 1;
            } else if (getCoordinate()[1] == 2 && getCoordinate()[2] > 0) {
                getCoordinate()[2] = getCoordinate()[2] - 1;
            } else {
                getCoordinate()[1] = getCoordinate()[1] - 1;
            }
        } else {
            if (getCoordinate()[1] == 0 && getCoordinate()[2] > 0) {
                getCoordinate()[2] = getCoordinate()[2] - 1;
            } else if (getCoordinate()[1] < 2 && getCoordinate()[2] == 0) {
                getCoordinate()[1] = getCoordinate()[1] + 1;
            } else if (getCoordinate()[1] == 2 && getCoordinate()[2] < 2) {
                getCoordinate()[2] = getCoordinate()[2] + 1;
            } else {
                getCoordinate()[1] = getCoordinate()[1] - 1;
            }
        }
    }
}
