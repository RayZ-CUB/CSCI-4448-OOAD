package com.rz.project2.map;

import com.rz.project2.adventurer.Adventurer;
import com.rz.project2.creature.Creature;

import java.util.ArrayList;
import java.util.HashMap;

public class Room {

    private final int z;  //level
    private final int y;
    private final int x;
    private HashMap<String, Adventurer> adventurers;
    private final HashMap<String, Creature> creatures;
    private final ArrayList<Room> adjacentRooms;


    public Room(int z, int y, int x) {  // constructor
        this.z = z;
        this.y = y;
        this.x = x;
        this.adventurers = new HashMap<>();
        this.creatures = new HashMap<>();
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

    public HashMap<String, Adventurer> getAdventurers() {
        return adventurers;
    }

    public void setAdventurers(HashMap<String, Adventurer> adventurers) {
        this.adventurers = adventurers;
    }

    public HashMap<String, Creature> getCreatures() {
        return creatures;
    }

    public ArrayList<Room> getAdjacentRooms() {
        return adjacentRooms;
    }

    @Override
    public String toString() {
        return z + "-" + y + "-" + x;
    }
}
