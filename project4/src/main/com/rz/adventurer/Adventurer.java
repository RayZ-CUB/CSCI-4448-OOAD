package com.rz.adventurer;


import com.rz.Constants;
import com.rz.map.Room;
import com.rz.skill.combat.Combat;
import com.rz.skill.search.Search;
import com.rz.treasure.Armor;
import com.rz.treasure.Gem;
import com.rz.treasure.Sword;
import com.rz.treasure.Treasure;

import java.util.ArrayList;
import java.util.Random;


//Use encapsulation in Adventurer class, accessibility of the features in adventurer class. Some methods set to private, and need to use setter and getter to access the value of that variable.

//Also use inheritance, Brawler, Runner, Sneaker and Thief use this adventurer class as a super class.

public class Adventurer {
    private String name;        // User defined name
    private String type;        // Adventurer type
    private int hp;             // Damage capacity
    public final Combat combat;
    public final Search search;
    private boolean[] treasuresSearched = new boolean[6];       // How many types of treasure found
    public Armor armor;
    public Gem gem;
    public Sword sword;
    private Room room;  //adventurers, creatures and gameMap share same room. So, the adventures' room can have same identity(memory address) as creatures' and gameMap's
    private int[] coordinate = new int[3];

    public Adventurer(String name, String type, int hp, Combat combat, Search search) {
        this.coordinate[0] = 0;
        this.coordinate[1] = 1;
        this.coordinate[2] = 1;
        this.name = name;
        this.type = type;
        this.hp = hp;
        this.combat = combat;
        this.search = search;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
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

    public int[] getCoordinate() {
        return coordinate;
    }

    public boolean[] getTreasuresSearched() {
        return treasuresSearched;
    }

    public void move(String nextRoomNumber) {
        ArrayList<Room> adjacentRooms = room.getAdjacentRooms();
        Room nextRoom = null;

        // Find nextRoom
        for (Room room : adjacentRooms) {
            if (nextRoomNumber.equals(room.toString())) {
                nextRoom = room;
            }
        }

        // If no nextRoom, throw exception
        if (nextRoom == null) {
            throw new RuntimeException(Constants.INVALID_ROOM_NUMBER);
        }

        coordinate[0] = nextRoom.getZ();
        coordinate[1] = nextRoom.getY();
        coordinate[2] = nextRoom.getX();
    }

    public void updateTreasuresSearched(Treasure treasure) {
        String treasureName = treasure.name;
        switch (treasureName) {
            case Constants.ARMOR_NAME -> treasuresSearched[0] = true;
            case Constants.GEM_NAME -> treasuresSearched[1] = true;
            case Constants.PORTAL_NAME -> treasuresSearched[2] = true;
            case Constants.POTION_NAME -> treasuresSearched[3] = true;
            case Constants.SWORD_NAME -> treasuresSearched[4] = true;
            case Constants.TRAP_NAME -> treasuresSearched[5] = true;
        }
    }


    public void portalMove() {
        Random random = new Random();
        coordinate[0] = random.nextInt(4) + 1;
        coordinate[1] = random.nextInt(3);
        coordinate[2] = random.nextInt(3);
    }

    public String currentRoomNumber() {
        return coordinate[0] + "-" + coordinate[1] + "-" + coordinate[2];
    }
}
