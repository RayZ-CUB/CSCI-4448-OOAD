package com.rz.project2;

import com.rz.project2.adventurer.*;
import com.rz.project2.creature.Blinker;
import com.rz.project2.creature.Creature;
import com.rz.project2.creature.Orbiter;
import com.rz.project2.creature.Seeker;
import com.rz.project2.map.GameMap;
import com.rz.project2.map.Room;

import java.util.ArrayList;
import java.util.HashMap;

public class GameEngine {
    public static void main(String[] args) {
        HashMap<String, Adventurer> adventurers = initAdventurers();
        HashMap<String, Creature> creatures = initCreature();
        GameMap gameMap = initGameMap(adventurers, creatures);
        System.out.println(gameMap);
        finishCurrentTurn(gameMap);
        System.out.println(gameMap);
        finishCurrentTurn(gameMap);
        System.out.println(gameMap);
        finishCurrentTurn(gameMap);
        System.out.println(gameMap);
        finishCurrentTurn(gameMap);
        System.out.println(gameMap);
        finishCurrentTurn(gameMap);
        System.out.println(gameMap);
        finishCurrentTurn(gameMap);
        System.out.println(gameMap);
        finishCurrentTurn(gameMap);
        System.out.println(gameMap);
    }

    private static HashMap<String, Adventurer> initAdventurers() {
        HashMap<String, Adventurer> adventurers = new HashMap<>();
        Brawler brawler = new Brawler();
        Thief thief = new Thief();
        Sneaker sneaker = new Sneaker();
        Runner runner = new Runner();

        adventurers.put(brawler.getName(), brawler);
        adventurers.put(thief.getName(), thief);
        adventurers.put(sneaker.getName(), sneaker);
        adventurers.put(runner.getName(), runner);

        return adventurers;
    }

    private static HashMap<String, Creature> initCreature() {
        HashMap<String, Creature> creatures = new HashMap<>();
        for (int i = 0; i < 4; i++) {
            Blinker blinker = new Blinker();
            Orbiter orbiter = new Orbiter();
            Seeker seeker = new Seeker();
            creatures.put(Constants.BLINKER_NAME + i, blinker);
            creatures.put(Constants.ORBITER_NAME + i, orbiter);
            creatures.put(Constants.SEEKER_NAME + i, seeker);
        }

        return creatures;
    }

    private static GameMap initGameMap(HashMap<String, Adventurer> adventurers, HashMap<String, Creature> creatures) {
        GameMap gameMap = new GameMap();

        // Put adventurers into entrance room
        Room entranceRoom = gameMap.rooms.get(Constants.ENTRANCE_ROOM);
        entranceRoom.setAdventurers(adventurers);

        // Initialize turn count
        gameMap.setTurnCount(0);

        // Initialize each adventure
        gameMap.brawler = (Brawler) adventurers.get(Constants.BRAWLER_NAME);
        gameMap.runner = (Runner) adventurers.get(Constants.RUNNER_NAME);
        gameMap.sneaker = (Sneaker) adventurers.get(Constants.SNEAKER_NAME);
        gameMap.thief = (Thief) adventurers.get(Constants.THIEF_NAME);

        // Initialize room information for each adventurer
        for (String key : adventurers.keySet()) {
            adventurers.get(key).setRoom(entranceRoom);
        }

        // Initialize creatures
        gameMap.creatures = creatures;

        // Initialize creature counts
        gameMap.setBlinkerCount(4);
        gameMap.setOrbiterCount(4);
        gameMap.setSeekerCount(4);

        // Put creatures into corresponding rooms
        for (String key : creatures.keySet()) {
            Creature creature = creatures.get(key);
            Room startingRoom = gameMap.rooms.get(creature.currentRoomNumber());
            startingRoom.getCreatures().put(key, creature);
            creature.setRoom(startingRoom);
        }

        initializeAdjacentRooms(gameMap);

        return gameMap;
    }

    private static void finishCurrentTurn(GameMap gameMap) {
        moveAdventurers(gameMap);
        moveCreatures(gameMap);
        gameMap.setTurnCount(gameMap.getTurnCount() + 1);
    }

    private static void moveAdventurers(GameMap gameMap) {
        // Delete adventurer information of original rooms
        gameMap.brawler.getRoom().getAdventurers().remove(Constants.BRAWLER_NAME);
        gameMap.runner.getRoom().getAdventurers().remove(Constants.RUNNER_NAME);
        gameMap.sneaker.getRoom().getAdventurers().remove(Constants.SNEAKER_NAME);
        gameMap.thief.getRoom().getAdventurers().remove(Constants.THIEF_NAME);

        // Move all adventurers in a turn
        gameMap.brawler.move();
        gameMap.runner.move();
        gameMap.sneaker.move();
        gameMap.thief.move();

        // Add adventurer information of new rooms
        Room newRoom = gameMap.rooms.get(gameMap.brawler.currentRoomNumber());
        newRoom.getAdventurers().put(Constants.BRAWLER_NAME, gameMap.brawler);
        gameMap.brawler.setRoom(newRoom);

        newRoom = gameMap.rooms.get(gameMap.runner.currentRoomNumber());
        newRoom .getAdventurers().put(Constants.RUNNER_NAME, gameMap.runner);
        gameMap.runner.setRoom(newRoom);

        newRoom = gameMap.rooms.get(gameMap.sneaker.currentRoomNumber());
        newRoom.getAdventurers().put(Constants.SNEAKER_NAME, gameMap.sneaker);
        gameMap.sneaker.setRoom(newRoom);

        newRoom = gameMap.rooms.get(gameMap.thief.currentRoomNumber());
        newRoom.getAdventurers().put(Constants.THIEF_NAME, gameMap.thief);
        gameMap.thief.setRoom(newRoom);
    }

    private static void moveCreatures(GameMap gameMap) {
        // TODO: Helper
        for (int i = 0; i < 4; i++) {
            // Move orbiters
            String orbiterKey = Constants.ORBITER_NAME + i;
            Creature creature = gameMap.creatures.get(orbiterKey);
            if (!(creature instanceof Orbiter)) {
                throw new RuntimeException("Orbiter initialization failure.");
            }
            Orbiter orbiter = (Orbiter) creature;
            Room oldRoom = orbiter.getRoom();
            oldRoom.getCreatures().remove(orbiterKey);
            orbiter.move();
            Room newRoom = gameMap.rooms.get(orbiter.currentRoomNumber());
            orbiter.setRoom(newRoom);
            newRoom.getCreatures().put(orbiterKey, orbiter);

            // Move blinkers
            String blinkerKey = Constants.BLINKER_NAME + i;
            creature = gameMap.creatures.get(blinkerKey);
            if (!(creature instanceof Blinker)) {
                throw new RuntimeException("blinker initialization failure.");
            }
            Blinker blinker = (Blinker) creature;
            oldRoom = blinker.getRoom();
            oldRoom.getCreatures().remove(blinkerKey);
            blinker.move();
            newRoom = gameMap.rooms.get(blinker.currentRoomNumber());
            blinker.setRoom(newRoom);
            newRoom.getCreatures().put(blinkerKey, blinker);

            // Move seekers
        }
    }

    private static void initializeAdjacentRooms(GameMap gameMap) {
        HashMap<String, Room> rooms = gameMap.rooms;
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
}
