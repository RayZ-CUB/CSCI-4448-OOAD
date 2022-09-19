package com.rz.project2;

import com.rz.project2.adventurer.*;
import com.rz.project2.creature.Blinker;
import com.rz.project2.creature.Creature;
import com.rz.project2.creature.Orbiter;
import com.rz.project2.creature.Seeker;
import com.rz.project2.map.GameMap;

import java.util.HashMap;

public class GameEngine {
    public static void main(String[] args) {
        HashMap<String, Adventurer> adventurers = initializeAdventurers();
        HashMap<String, Creature> creatures = initializeCreature();
        GameMap gameMap = initializeGameMap(adventurers, creatures);
        System.out.println(gameMap);
        gameMap.moveAdventurers();
        System.out.println(gameMap);
        gameMap.moveAdventurers();
        System.out.println(gameMap);
        gameMap.moveAdventurers();
        System.out.println(gameMap);
    }

    private static HashMap<String, Adventurer> initializeAdventurers() {
        HashMap<String, Adventurer> adventurers = new HashMap<>();
        Brawler brawler = new Brawler();
        Thief thief = new Thief();
        Sneaker sneaker = new Sneaker();
        Runner runner = new Runner();

        adventurers.put("B", brawler);
        adventurers.put("T", thief);
        adventurers.put("S", sneaker);
        adventurers.put("R", runner);

        return adventurers;
    }

    private static HashMap<String, Creature> initializeCreature() {
        HashMap<String, Creature> creatures = new HashMap<>();
        for (int i = 0; i < 4; i++) {
            creatures.put("B" + i, new Blinker());
            creatures.put("O" + i, new Orbiter());
            creatures.put("S" + i, new Seeker());
        }

        return creatures;
    }

    private static GameMap initializeGameMap(HashMap<String, Adventurer> adventurers, HashMap<String, Creature> creatures) {
        GameMap gameMap = new GameMap();

        // Initialize adventures
        gameMap.brawler = (Brawler) adventurers.get("B");
        gameMap.runner = (Runner) adventurers.get("R");
        gameMap.sneaker = (Sneaker) adventurers.get("S");
        gameMap.thief = (Thief) adventurers.get("T");

        // Initialize creature counts
        gameMap.setBlinkerCount(4);
        gameMap.setOrbiterCount(4);
        gameMap.setSeekerCount(4);

        gameMap.setTurnCount(1);

        // Put adventurers into entrance room
        gameMap.rooms.get("0-1-1").setAdventurers(adventurers);

        // Put creatures into corresponding rooms
        for (String key : creatures.keySet()) {
            Creature creature = creatures.get(key);
            String roomNumber = creature.getRoom().toString();
            gameMap.rooms.get(roomNumber).getCreatures().put(key, creature);
        }

        return gameMap;
    }
}
