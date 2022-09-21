package com.rz.project2.creature;

import com.rz.project2.map.Room;

import java.util.Random;

public abstract class Creature {
    private String name;
    private Room room;


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

    public abstract void move();

    public int rollDices() {
        Random random = new Random();
        return random.nextInt(6) + 1 + random.nextInt(6) + 1;
    }
}
