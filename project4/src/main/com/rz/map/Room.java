package com.rz.map;

import com.rz.adventurer.Adventurer;
import com.rz.creature.Creature;
import com.rz.treasure.Treasure;

import java.util.ArrayList;
import java.util.HashMap;

public class Room {

    private final int z;  //level
    private final int y;
    private final int x;
    private Adventurer adventurer;
    private final HashMap<String, Creature> creatures;

    private final HashMap<String, Treasure> treasures;
    private final ArrayList<Room> adjacentRooms;


    public Room(int z, int y, int x) {  // constructor
        this.z = z;
        this.y = y;
        this.x = x;
        this.creatures = new HashMap<>();
        this.treasures = new HashMap<>();
        this.adjacentRooms = new ArrayList<>();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public Adventurer getAdventurer() {
        return adventurer;
    }

    public void setAdventurer(Adventurer adventurer) {
        this.adventurer = adventurer;
    }

    public HashMap<String, Creature> getCreatures() {
        return creatures;
    }
    public HashMap<String, Treasure> getTreasures() {
        return treasures;
    }

    public ArrayList<Room> getAdjacentRooms() {
        return adjacentRooms;
    }

    @Override
    public String toString() {
        return z + "-" + y + "-" + x;
    }
}
