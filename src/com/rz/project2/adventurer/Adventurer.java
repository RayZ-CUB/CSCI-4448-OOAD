package com.rz.project2.adventurer;

import com.rz.project2.map.Room;

public class Adventurer {
    private String name;
    private int damage = 0;
    private int treasureCount;
    private Room room = new Room(0,1,1);


    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        if (damage < 0 || damage > 3) {
            throw new RuntimeException("Invalid damage");
        }
        this.damage = damage;
    }

    public int getTreasureCount() {
        return treasureCount;
    }

    public void setTreasureCount(int treasureCount) {
        if (treasureCount < 0 || treasureCount > 10) {
            throw new RuntimeException("Invalid treasure count");
        }
        this.treasureCount = treasureCount;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
