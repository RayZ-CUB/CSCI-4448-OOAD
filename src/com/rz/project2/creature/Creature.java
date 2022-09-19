package com.rz.project2.creature;

import com.rz.project2.map.Room;

public class Creature {
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
}
