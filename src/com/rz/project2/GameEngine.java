package com.rz.project2;

import com.rz.project2.adventurer.*;
import com.rz.project2.creature.Blinker;
import com.rz.project2.creature.Creature;
import com.rz.project2.creature.Orbiter;
import com.rz.project2.creature.Seeker;
import com.rz.project2.map.GameMap;
import com.rz.project2.map.Room;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

public class GameEngine {
    public static void main(String[] args) {
        HashMap<String, Adventurer> adventurers = initAdventurers();
        HashMap<String, Creature> creatures = initCreature();
        GameMap gameMap = initGameMap(adventurers, creatures);
        boolean flag = true;
        System.out.println(gameMap);
        while (flag) {
            play(gameMap);
            System.out.println(gameMap);
            if (checkIfAdventurersWin(gameMap).equals(Constants.ADVENTURER_WIN)) {
                System.out.println("Adventurers win!");
                flag = false;
            } else if (checkIfAdventurersWin(gameMap).equals(Constants.CREATURE_WIN)) {
                System.out.println("Creatures win!");
                flag = false;
            } else {
            }
        }
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

        return gameMap;
    }

    private static void play(GameMap gameMap) {
        moveAdventurers(gameMap);
        fightAndSearchA(gameMap);
        moveRunner(gameMap);
        if (gameMap.runner != null) {
            fightAndSearch(gameMap, gameMap.runner);
        }
        moveCreatures(gameMap);
        fightAndSearchC(gameMap);
        gameMap.setTurnCount(gameMap.getTurnCount() + 1);
    }

    private static void moveAdventurers(GameMap gameMap) {
        moveBrawler(gameMap);
        moveRunner(gameMap);
        moveSneaker(gameMap);
        moveThief(gameMap);
    }

    private static void moveBrawler(GameMap gameMap) {
        if (gameMap.brawler == null) {
            return;
        }
        // Delete brawler in original room
        gameMap.brawler.getRoom().getAdventurers().remove(Constants.BRAWLER_NAME);
        // Move brawler in a turn
        gameMap.brawler.move();
        // Add brawler into new room
        Room newRoom = gameMap.rooms.get(gameMap.brawler.currentRoomNumber());
        newRoom.getAdventurers().put(Constants.BRAWLER_NAME, gameMap.brawler);
        gameMap.brawler.setRoom(newRoom);
    }

    private static void moveRunner(GameMap gameMap) {
        if (gameMap.runner == null) {
            return;
        }
        gameMap.runner.getRoom().getAdventurers().remove(Constants.RUNNER_NAME);
        gameMap.runner.move();
        Room newRoom = gameMap.rooms.get(gameMap.runner.currentRoomNumber());
        newRoom.getAdventurers().put(Constants.RUNNER_NAME, gameMap.runner);
        gameMap.runner.setRoom(newRoom);
    }

    private static void moveSneaker(GameMap gameMap) {
        if (gameMap.sneaker == null) {
            return;
        }
        gameMap.sneaker.getRoom().getAdventurers().remove(Constants.SNEAKER_NAME);
        gameMap.sneaker.move();
        Room newRoom = gameMap.rooms.get(gameMap.sneaker.currentRoomNumber());
        newRoom.getAdventurers().put(Constants.SNEAKER_NAME, gameMap.sneaker);
        gameMap.sneaker.setRoom(newRoom);
    }

    private static void moveThief(GameMap gameMap) {
        if (gameMap.thief == null) {
            return;
        }
        gameMap.thief.getRoom().getAdventurers().remove(Constants.THIEF_NAME);
        gameMap.thief.move();
        Room newRoom = gameMap.rooms.get(gameMap.thief.currentRoomNumber());
        newRoom.getAdventurers().put(Constants.THIEF_NAME, gameMap.thief);
        gameMap.thief.setRoom(newRoom);
    }

    private static void moveCreatures(GameMap gameMap) {
        for (String key : gameMap.creatures.keySet()) {
            if (key.contains("O")) {
                Creature creature = gameMap.creatures.get(key);
                if (!(creature instanceof Orbiter orbiter)) {
                    throw new RuntimeException("Orbiter initialization failure.");
                }
                Room oldRoom = orbiter.getRoom();
                oldRoom.getCreatures().remove(key);
                orbiter.move();
                Room newRoom = gameMap.rooms.get(orbiter.currentRoomNumber());
                orbiter.setRoom(newRoom);
                newRoom.getCreatures().put(key, orbiter);
            } else if (key.contains("B")) {
                // Move blinkers
                Creature creature = gameMap.creatures.get(key);
                if (!(creature instanceof Blinker blinker)) {
                    throw new RuntimeException("Blinker initialization failure.");
                }
                Room oldRoom = blinker.getRoom();
                oldRoom.getCreatures().remove(key);
                blinker.move();
                Room newRoom = gameMap.rooms.get(blinker.currentRoomNumber());
                blinker.setRoom(newRoom);
                newRoom.getCreatures().put(key, blinker);
            } else {
                // Move seekers
                Creature creature = gameMap.creatures.get(key);
                if (!(creature instanceof Seeker seeker)) {
                    throw new RuntimeException("Seeker initialization failure.");
                }
                Room oldRoom = seeker.getRoom();
                oldRoom.getCreatures().remove(key);
                seeker.move();
                Room newRoom = gameMap.rooms.get(seeker.currentRoomNumber());
                seeker.setRoom(newRoom);
                newRoom.getCreatures().put(key, seeker);
            }
        }
    }

    private static void fightAndSearchA(GameMap gameMap) {
        if (gameMap.brawler != null) {
            fightAndSearch(gameMap, gameMap.brawler);
        }

        if (gameMap.runner != null) {
            fightAndSearch(gameMap, gameMap.runner);
        }

        if (gameMap.sneaker != null) {
            fightAndSearch(gameMap, gameMap.sneaker);
        }


        if (gameMap.thief != null) {
            fightAndSearch(gameMap, gameMap.thief);
        }
    }

    private static void fightAndSearchC(GameMap gameMap) {
        // https://www.baeldung.com/java-concurrentmodificationexception
        for (Iterator<Map.Entry<String, Creature>> iterator = gameMap.creatures.entrySet().iterator(); iterator.hasNext(); ) {
            Map.Entry<String, Creature> next = iterator.next();
            String key = next.getKey();
            Creature creature = next.getValue();
            Room currentRoom = creature.getRoom();
            HashMap<String, Adventurer> adventurersInRoom = currentRoom.getAdventurers();
            if (adventurersInRoom.isEmpty()) {
                return;
            }

            // Let each Adventurer in this room fight with this creature
            for (String adventurerKey : adventurersInRoom.keySet()) {
                Adventurer adventurer = adventurersInRoom.get(adventurerKey);
                int adventurerAttack = adventurer.attack();
                int creatureAttack = creature.attack();

                if (adventurerAttack > creatureAttack) {
                    currentRoom.getCreatures().remove(key);
                    iterator.remove();
                    updateCreatureCount(gameMap, creature);
                    break;
                } else if (adventurerAttack < creatureAttack) {
                    adventurer.setDamage(adventurer.getDamage() + 1);
                } else {
                }

                checkAdventurersDamage(adventurer, currentRoom, gameMap);
            }
        }
    }

    private static void fightAndSearch(GameMap gameMap, Adventurer adventurer) {
        Room currentRoom = adventurer.getRoom();
        HashMap<String, Creature> creaturesInRoom = currentRoom.getCreatures();
        if (creaturesInRoom.isEmpty()) {
            if (adventurer.searchTreasure()) {
                gameMap.setTotalTreasureCount(gameMap.getTotalTreasureCount() + 1);
            }
        } else {
            // Sneaker has a 50% chance not to have to fight Creatures found in their Room.
            if(adventurer.getName() == Constants.SNEAKER_NAME){
                Random random = new Random();
                if (random.nextBoolean()) {
                    return;
                }
            }

            for (Iterator<Map.Entry<String, Creature>> iterator = creaturesInRoom.entrySet().iterator(); iterator.hasNext(); ) {
                Map.Entry<String, Creature> next = iterator.next();
                String key = next.getKey();
                Creature creature = next.getValue();

                int adventurerAttack = adventurer.attack();
                int creatureAttack = creature.attack();

                if (adventurerAttack > creatureAttack) {
                    iterator.remove();
                    gameMap.creatures.remove(key);
                    updateCreatureCount(gameMap, creature);
                } else if (adventurerAttack < creatureAttack) {
                    if (adventurer.getDamage() < 3) {
                        adventurer.setDamage(adventurer.getDamage() + 1);
                    }
                } else {
                }

                checkAdventurersDamage(adventurer, currentRoom, gameMap);
            }
        }
    }

    private static void checkAdventurersDamage(Adventurer adventurer, Room currentRoom, GameMap gameMap) {
        if (adventurer.getDamage() == 3) {
            currentRoom.getAdventurers().remove(adventurer.getName());
            if (adventurer instanceof Brawler) {
                gameMap.brawler = null;
            } else if (adventurer instanceof Runner) {
                gameMap.runner = null;
            } else if (adventurer instanceof Sneaker) {
                gameMap.sneaker = null;
            } else {
                gameMap.thief = null;
            }
        }
    }

    private static void updateCreatureCount(GameMap gameMap, Creature creature) {
        if (creature instanceof Blinker) {
            gameMap.setBlinkerCount(gameMap.getBlinkerCount() - 1);
        } else if (creature instanceof Orbiter) {
            gameMap.setOrbiterCount(gameMap.getOrbiterCount() - 1);
        } else {
            gameMap.setSeekerCount(gameMap.getSeekerCount() - 1);
        }
    }

    private static String checkIfAdventurersWin(GameMap gameMap) {
        if (gameMap.getTotalTreasureCount() >= 10 || gameMap.creatures.size() == 0) {
            return Constants.ADVENTURER_WIN;
        } else if (gameMap.brawler == null && gameMap.runner == null && gameMap.sneaker == null && gameMap.thief == null) {
            return Constants.CREATURE_WIN;
        } else {
            // No winner
            return Constants.NO_WINNER;
        }
    }
}
