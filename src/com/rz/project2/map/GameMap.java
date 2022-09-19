package com.rz.project2.map;

import com.rz.project2.adventurer.*;
import com.rz.project2.creature.Creature;

import java.util.HashMap;

public class GameMap {
    public HashMap<String, Room> rooms = new HashMap<>();
    public Brawler brawler;
    public Runner runner;
    public Sneaker sneaker;
    public Thief thief;
    private int blinkerCount;
    private int orbiterCount;
    private int seekerCount;
    private int turnCount;

    public GameMap() {
        rooms.put("0-1-1", new Room(0, 1, 1));   //
        for (int z = 1; z < 5; z++) {
            for (int y = 0; y < 3; y++) {
                for (int x = 0; x < 3; x++) {
                    rooms.put(z + "-" + y + "-" + x, new Room(z, y, x));
                }
            }
        }
    }

    public int getBlinkerCount() {
        return blinkerCount;
    }

    public void setBlinkerCount(int blinkerCount) {
        if (blinkerCount < 0 || blinkerCount > 4) {
            throw new RuntimeException("Invalid blinker count");
        }
        this.blinkerCount = blinkerCount;
    }

    public int getOrbiterCount() {
        return orbiterCount;
    }

    public void setOrbiterCount(int orbiterCount) {
        if (orbiterCount < 0 || orbiterCount > 4) {
            throw new RuntimeException("Invalid orbiter count");
        }
        this.orbiterCount = orbiterCount;
    }

    public int getSeekerCount() {
        return seekerCount;
    }

    public void setSeekerCount(int seekerCount) {
        if (seekerCount < 0 || seekerCount > 4) {
            throw new RuntimeException("Invalid seeker count");
        }
        this.seekerCount = seekerCount;
    }

    public int getTurnCount() {
        return turnCount;
    }

    public void setTurnCount(int turnCount) {
        if (turnCount < 1) {
            throw new RuntimeException("Invalid turn count");
        }
        this.turnCount = turnCount;
    }

    /***
     * Concatenating adventurers' names
     */
    private void concatNames(HashMap<String, Room> rooms, String roomNumber, StringBuilder output) {
        Room room = rooms.get(roomNumber);
        output.append(room).append(": ");
        if (!room.getAdventurers().isEmpty()) {   // If there is an adventurer, then print out
            HashMap<String, Adventurer> adventurers = room.getAdventurers();
            for (String key : adventurers.keySet()) {
                output.append(adventurers.get(key).getName());
            }
        } else {
            output.append("-");
        }
        output.append(" : ");

        if (!room.getCreatures().isEmpty()) {   // If there is a creature, then print out
            HashMap<String, Creature> creatures = room.getCreatures();
            for (String key : creatures.keySet()) {
                output.append(creatures.get(key).getName());
            }
        } else {
            output.append("-");
        }

        output.append("\t");
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();

        // Print out turn count
        output.append("RotLA Turn " + turnCount + ":\n");

        // Print out entrance room
        concatNames(rooms, "0-1-1", output);
        output.append("\n");

        // Print out each level
        for (int z = 1; z < 5; z++) {
            for (int y = 0; y < 3; y++) {
                for (int x = 0; x < 3; x++) {
                    String roomNumber = z + "-" + y + "-" + x;
                    concatNames(rooms, roomNumber, output);
                }
                output.append("\n");
            }
        }

        output.append("\n");

        // Print out adventurers' information
        output.append("Brawler - " + brawler.getTreasureCount() + " Treasure(s) - " + brawler.getDamage() + " Damage\n");
        output.append("Sneaker - " + sneaker.getTreasureCount() + " Treasure(s) - " + sneaker.getDamage() + " Damage\n");
        output.append("Runner - " + runner.getTreasureCount() + " Treasure(s) - " + runner.getDamage() + " Damage\n");
        output.append("Thief - " + thief.getTreasureCount() + " Treasure(s) - " + thief.getDamage() + " Damage\n\n");

        // Print out creatures' count
        output.append("Orbiters - " + orbiterCount + " Remaining\n");
        output.append("Seekers - " + seekerCount + " Remaining\n");
        output.append("Blinkers - " + blinkerCount + " Remaining\n");

        return output.toString();
    }
}
