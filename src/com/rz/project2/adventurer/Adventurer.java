package com.rz.project2.adventurer;

import com.rz.project2.map.Room;

public class Adventurer {
    private int HP = 3;
    private int treasureCount;
    private Room room = new Room(0,1,1);


    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        if (HP < 0 || HP > 3) {
            throw new RuntimeException("Invalid HP");
        }
        this.HP = HP;
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
}
