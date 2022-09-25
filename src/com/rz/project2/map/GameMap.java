package com.rz.project2.map;

import com.rz.project2.Constants;
import com.rz.project2.adventurer.*;
import com.rz.project2.creature.Creature;

import java.util.ArrayList;
import java.util.HashMap;

public class GameMap {
    public HashMap<String, Room> rooms = new HashMap<>();
    public Brawler brawler;
    public Runner runner;
    public Sneaker sneaker;
    public Thief thief;
    public HashMap<String, Creature> creatures;
    private int blinkerCount;
    private int orbiterCount;
    private int seekerCount;
    private int turnCount;
    private int totalTreasureCount;

    public GameMap() {
        rooms.put(Constants.ENTRANCE_ROOM, new Room(0, 1, 1));
        for (int z = 1; z < 5; z++) {
            for (int y = 0; y < 3; y++) {
                for (int x = 0; x < 3; x++) {
                    rooms.put(z + "-" + y + "-" + x, new Room(z, y, x));
                }
            }
        }
        initAdjacentRooms();
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
        if (turnCount < 0) {
            throw new RuntimeException("Invalid turn count");
        }
        this.turnCount = turnCount;
    }

    public int getTotalTreasureCount() {
        return totalTreasureCount;
    }

    public void setTotalTreasureCount(int totalTreasureCount) {
        this.totalTreasureCount = totalTreasureCount;
    }

    /***
     * Concatenating adventurers' names
     */
    private void concatNames(HashMap<String, Room> rooms, String roomNumber, StringBuilder output) {
        Room room = rooms.get(roomNumber);
        output.append(room).append(": ");
        // If there is an adventurer, then print out
        if (!room.getAdventurers().isEmpty()) {
            HashMap<String, Adventurer> adventurers = room.getAdventurers();
            for (String key : adventurers.keySet()) {
                output.append(adventurers.get(key).getName());
            }
        } else {
            output.append("-");
        }
        output.append(" : ");

        // If there is a creature, then print out
        if (!room.getCreatures().isEmpty()) {
            HashMap<String, Creature> creatures = room.getCreatures();
            for (String key : creatures.keySet()) {
                output.append(creatures.get(key).getName());
            }
        } else {
            output.append("-");
        }

        output.append("\t\t\t");
    }

    private void initAdjacentRooms() {
        int i;

        // Set up or down adjacent rooms for center rooms
        rooms.get("1-1-1").getAdjacentRooms().add(rooms.get("2-1-1"));
        rooms.get("2-1-1").getAdjacentRooms().add(rooms.get("1-1-1"));
        rooms.get("2-1-1").getAdjacentRooms().add(rooms.get("3-1-1"));
        rooms.get("3-1-1").getAdjacentRooms().add(rooms.get("2-1-1"));
        rooms.get("3-1-1").getAdjacentRooms().add(rooms.get("4-1-1"));
        rooms.get("4-1-1").getAdjacentRooms().add(rooms.get("3-1-1"));

        // Set adjacent rooms vertically
        for (int z = 1; z < 5; z++) {
            for (int y = 0; y < 3; y++) {
                if (y == 0) {
                    for (int x = 0; x < 3; x++) {
                        String roomNumber = z + "-" + y + "-" + x;
                        ArrayList<Room> adjacentRooms = rooms.get(roomNumber).getAdjacentRooms();
                        i = y + 1;
                        adjacentRooms.add(rooms.get(z + "-" + i + "-" + x));
                    }
                } else if (y == 1) {
                    for (int x = 0; x < 3; x++) {
                        String roomNumber = z + "-" + y + "-" + x;
                        ArrayList<Room> adjacentRooms = rooms.get(roomNumber).getAdjacentRooms();
                        i = y + 1;
                        adjacentRooms.add(rooms.get(z + "-" + i + "-" + x));
                        i = y - 1;
                        adjacentRooms.add(rooms.get(z + "-" + i + "-" + x));
                    }
                } else {
                    for (int x = 0; x < 3; x++) {
                        String roomNumber = z + "-" + y + "-" + x;
                        ArrayList<Room> adjacentRooms = rooms.get(roomNumber).getAdjacentRooms();
                        i = y - 1;
                        adjacentRooms.add(rooms.get(z + "-" + i + "-" + x));
                    }
                }
            }
        }

        // Set adjacent rooms horizontally
        for (int z = 1; z < 5; z++) {
            for (int x = 0; x < 3; x++) {
                if (x == 0) {
                    for (int y = 0; y < 3; y++) {
                        String roomNumber = z + "-" + y + "-" + x;
                        ArrayList<Room> adjacentRooms = rooms.get(roomNumber).getAdjacentRooms();
                        i = x + 1;
                        adjacentRooms.add(rooms.get(z + "-" + y + "-" + i));
                    }
                } else if (x == 1) {
                    for (int y = 0; y < 3; y++) {
                        String roomNumber = z + "-" + y + "-" + x;
                        ArrayList<Room> adjacentRooms = rooms.get(roomNumber).getAdjacentRooms();
                        i = x + 1;
                        adjacentRooms.add(rooms.get(z + "-" + y + "-" + i));
                        i = x - 1;
                        adjacentRooms.add(rooms.get(z + "-" + y + "-" + i));
                    }
                } else {
                    for (int y = 0; y < 3; y++) {
                        String roomNumber = z + "-" + y + "-" + x;
                        ArrayList<Room> adjacentRooms = rooms.get(roomNumber).getAdjacentRooms();
                        i = x - 1;
                        adjacentRooms.add(rooms.get(z + "-" + y + "-" + i));
                    }
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();

        // Print out turn count
        output.append("RotLA Turn ").append(turnCount).append(":\n");

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
        if (brawler != null) {
            output.append("Brawler - ").append(brawler.getTreasureCount()).append(" Treasure(s) - ").append(brawler.getDamage()).append(" Damage\n");
        } else {
            output.append("Brawler died. \n");
        }

        if (sneaker != null) {
            output.append("Sneaker - ").append(sneaker.getTreasureCount()).append(" Treasure(s) - ").append(sneaker.getDamage()).append(" Damage\n");
        } else {
            output.append("Sneaker died. \n");
        }

        if (runner != null) {
            output.append("Runner - ").append(runner.getTreasureCount()).append(" Treasure(s) - ").append(runner.getDamage()).append(" Damage\n");
        } else {
            output.append("Runner died. \n");
        }

        if (thief != null) {
            output.append("Thief - ").append(thief.getTreasureCount()).append(" Treasure(s) - ").append(thief.getDamage()).append(" Damage\n\n");
        } else {
            output.append("Thief died. \n\n");
        }

        // Print out creatures' count
        output.append("Orbiters - ").append(orbiterCount).append(" Remaining\n");
        output.append("Seekers - ").append(seekerCount).append(" Remaining\n");
        output.append("Blinkers - ").append(blinkerCount).append(" Remaining\n");

        return output.toString();
    }
}
