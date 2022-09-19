package com.rz.project2.map;

import com.rz.project2.adventurer.Adventurer;
import com.rz.project2.creature.Creature;

import java.util.HashMap;

public class Room {

    private int z;  //level
    private int y;
    private int x;
    private HashMap<String, Adventurer> adventurers = new HashMap<>();
    private HashMap<String, Creature> creatures = new HashMap<>();

    public Room(int z, int y, int x) {  // constructor
        this.z = z;
        this.y = y;
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        if (x < 0 || x > 2) {
            System.out.println("Invalid horizontal index");
        }
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        if (y < 0 || y > 2) {
            System.out.println("Invalid vertical index");
        }
        this.y = y;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        if (z < 0 || z > 4) {
            System.out.println("Invalid level index");
        }
        this.z = z;
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

    public void setCreatures(HashMap<String, Creature> creatures) {
        this.creatures = creatures;
    }

    @Override
    public String toString() {
        return z + "-" + y + "-" + x;
    }
}
