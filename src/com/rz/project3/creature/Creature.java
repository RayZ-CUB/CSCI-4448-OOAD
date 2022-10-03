package com.rz.project3.creature;

import com.rz.project3.map.Room;

import java.util.Random;


//In creature abstract class, using abstraction to abstract all attribute that creatures have.
public abstract class Creature {
    private String name;
    private Room room;
    // z-y-x
    private final int[] coordinate = new int[3];


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public int[] getCoordinate() {
        return coordinate;
    }

    public abstract void move();

    public int attack() {
        return rollDices();
    }

    private int rollDices() {
        Random random = new Random();
        return random.nextInt(6) + 1 + random.nextInt(6) + 1;
    }

    public String currentRoomNumber() {
        return coordinate[0] + "-" + coordinate[1] + "-" + coordinate[2];
    }
}
