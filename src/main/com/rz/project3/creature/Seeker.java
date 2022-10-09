package com.rz.project3.creature;

import com.rz.project3.Constants;
import com.rz.project3.map.Room;

import java.util.ArrayList;
import java.util.Random;

public class Seeker extends Creature {
    public Seeker(int id) {
        this.fullName = Constants.SEEKER_FULL_NAME;
        this.setName(Constants.SEEKER_NAME);
        this.id = id;

        Random random = new Random();   // https://stackoverflow.com/questions/363681/how-do-i-generate-random-integers-within-a-specific-range-in-java
        int z = random.nextInt(4) + 1;
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

        ArrayList<Room> adjacentRooms = getRoom().getAdjacentRooms();
        // Search for adventures in adjacent rooms
        for (Room adjacentRoom : adjacentRooms) {
            if (!adjacentRoom.getAdventurers().isEmpty()) {
                getCoordinate()[0] = adjacentRoom.getZ();
                getCoordinate()[1] = adjacentRoom.getY();
                getCoordinate()[2] = adjacentRoom.getX();
                return;
            }
        }
    }
}
