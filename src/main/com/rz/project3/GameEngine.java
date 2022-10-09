package com.rz.project3;

import com.rz.project3.adventurer.*;
import com.rz.project3.creature.Blinker;
import com.rz.project3.creature.Creature;
import com.rz.project3.creature.Orbiter;
import com.rz.project3.creature.Seeker;
import com.rz.project3.map.GameMap;
import com.rz.project3.map.Room;
import com.rz.project3.observer.Logger;
import com.rz.project3.observer.Publisher;
import com.rz.project3.observer.Tracker;
import com.rz.project3.skill.celebration.*;
import com.rz.project3.skill.combat.*;
import com.rz.project3.skill.search.CarefulSearch;
import com.rz.project3.skill.search.CarelessSearch;
import com.rz.project3.skill.search.QuickSearch;
import com.rz.project3.treasure.*;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

public class GameEngine {
    private static BufferedWriter writer;
    private static Publisher publisher;
    private static Tracker tracker;
    private static GameMap gameMap;

    public static void main(String[] args) throws IOException {
        HashMap<String, Adventurer> adventurers = initAdventurers();
        HashMap<String, Creature> creatures = initCreatures();
        HashMap<String, Treasure> treasures = initTreasures();
        BufferedWriter singleRunWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("src/main/resources/singleGameRun/SingleGameRun.txt")));
        gameMap = initGameMap(adventurers, creatures, treasures);
        tracker = new Tracker(singleRunWriter);
        boolean flag = true;

        System.out.println(gameMap);
//        writer.write(gameMap.toString());
//        writer.newLine();
        while (flag) {
            int turnCount = gameMap.getTurnCount() + 1;
            String fileName = "src/main/resources/singleGameRun/log/Logger-" + turnCount + ".txt";
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName)));
            publisher = new Publisher();
            publisher.addObserver(new Logger(writer));
            publisher.addObserver(tracker);

            play();
            System.out.println(gameMap);
//            writer.write(gameMap.toString());
//            writer.newLine();
//            writer.write("-------------------------------------------------------------------------------------------\n\n");
            if (checkIfAdventurersWin().equals(Constants.ADVENTURER_WIN)) {
                System.out.println(gameMap.winner);
//                writer.write(gameMap.winner);
                flag = false;
            } else if (checkIfAdventurersWin().equals(Constants.CREATURE_WIN)) {
                System.out.println(gameMap.winner);
//                writer.write(gameMap.winner);
                flag = false;
            }
            writer.close();
        }
        singleRunWriter.close();

        // MultipleGameRun
//        File multipleGameRun = new File("MultipleGameRun.txt");
//        FileOutputStream multipleOS = new FileOutputStream(multipleGameRun);
//        BufferedWriter multipleWriter = new BufferedWriter(new OutputStreamWriter(multipleOS));
//
//        for (int i = 1; i <= 30; i++) {
//            adventurers = initAdventurers();
//            creatures = initCreatures();
//            gameMap = initGameMap(adventurers, creatures, treasures);
//            flag = true;
//
//            while (flag) {
//                play(gameMap);
//                if (checkIfAdventurersWin(gameMap).equals(Constants.ADVENTURER_WIN)) {
//                    flag = false;
//                    printToMultiple(gameMap, i, multipleWriter);
//
//                } else if (checkIfAdventurersWin(gameMap).equals(Constants.CREATURE_WIN)) {
//                    flag = false;
//                    printToMultiple(gameMap, i, multipleWriter);
//                }
//            }
//        }
//
//        multipleWriter.close();
    }

    private static void printToMultiple(GameMap gameMap, int gameCount, BufferedWriter writer) throws IOException {
        // https://www.programcreek.com/2011/03/java-write-to-a-file-code-example/

//        StringBuilder output = new StringBuilder();

//        // Print out game count
//        output.append("Game: ").append(gameCount).append("\n\n");
//
//        // Print out turn count
//        output.append("RotLA Turn ").append(gameMap.getTurnCount()).append(":\n\n");
//        // Print out adventurers' information
//        if (gameMap.brawler != null) {
//            output.append("Brawler - ").append(gameMap.brawler.getTreasureCount()).append(" Treasure(s) - ").append(gameMap.brawler.getDamage()).append(" Damage\n");
//        } else {
//            output.append("Brawler died. \n");
//        }
//
//        if (gameMap.sneaker != null) {
//            output.append("Sneaker - ").append(gameMap.sneaker.getTreasureCount()).append(" Treasure(s) - ").append(gameMap.sneaker.getDamage()).append(" Damage\n");
//        } else {
//            output.append("Sneaker died. \n");
//        }
//
//        if (gameMap.runner != null) {
//            output.append("Runner - ").append(gameMap.runner.getTreasureCount()).append(" Treasure(s) - ").append(gameMap.runner.getDamage()).append(" Damage\n");
//        } else {
//            output.append("Runner died. \n");
//        }
//
//        if (gameMap.thief != null) {
//            output.append("Thief - ").append(gameMap.thief.getTreasureCount()).append(" Treasure(s) - ").append(gameMap.thief.getDamage()).append(" Damage\n\n");
//        } else {
//            output.append("Thief died. \n\n");
//        }
//
//        // Print out creatures' count
//        output.append("Orbiters - ").append(gameMap.getOrbiterCount()).append(" Remaining\n");
//        output.append("Seekers - ").append(gameMap.getSeekerCount()).append(" Remaining\n");
//        output.append("Blinkers - ").append(gameMap.getBlinkerCount()).append(" Remaining\n\n");
//        output.append(gameMap.winner).append("\n\n");
//        output.append("-------------------------------------------------------------------------------------------\n\n");

//        writer.write(output.toString());
    }

    private static HashMap<String, Adventurer> initAdventurers() {
        HashMap<String, Adventurer> adventurers = new HashMap<>();
        Brawler brawler = new Brawler(Constants.BRAWLER_NAME, Constants.BRAWLER_FULL_NAME, new ExpertCombat(), new CarelessSearch());
        Thief thief = new Thief(Constants.THIEF_NAME, Constants.THIEF_FULL_NAME, new TrainedCombat(), new CarefulSearch());
        Sneaker sneaker = new Sneaker(Constants.SNEAKER_NAME, Constants.SNEAKER_FULL_NAME, new StealthCombat(), new QuickSearch());
        Runner runner = new Runner(Constants.RUNNER_NAME, Constants.RUNNER_FULL_NAME, new UntrainedCombat(), new QuickSearch());

        adventurers.put(brawler.getName(), brawler);
        adventurers.put(thief.getName(), thief);
        adventurers.put(sneaker.getName(), sneaker);
        adventurers.put(runner.getName(), runner);

        return adventurers;
    }

    private static HashMap<String, Creature> initCreatures() {
        HashMap<String, Creature> creatures = new HashMap<>();
        for (int i = 0; i < 4; i++) {
            Blinker blinker = new Blinker(i);
            Orbiter orbiter = new Orbiter(i);
            Seeker seeker = new Seeker(i);
            creatures.put(Constants.BLINKER_NAME + i, blinker);
            creatures.put(Constants.ORBITER_NAME + i, orbiter);
            creatures.put(Constants.SEEKER_NAME + i, seeker);
        }

        return creatures;
    }

    private static HashMap<String, Treasure> initTreasures() {
        HashMap<String, Treasure> treasures = new HashMap<>();
        for (int i = 0; i < 4; i++) {
            treasures.put(Constants.ARMOR_NAME + i, new Armor(Constants.ARMOR_NAME, i));
            treasures.put(Constants.GEM_NAME + i, new Gem(Constants.GEM_NAME, i));
            treasures.put(Constants.PORTAL_NAME + i, new Portal(Constants.PORTAL_NAME, i));
            treasures.put(Constants.POTION_NAME + i, new Potion(Constants.POTION_NAME, i));
            treasures.put(Constants.SWORD_NAME + i, new Sword(Constants.SWORD_NAME, i));
            treasures.put(Constants.TRAP_NAME + i, new Trap(Constants.TRAP_NAME, i));
        }
        return treasures;
    }

    private static GameMap initGameMap(HashMap<String, Adventurer> adventurers, HashMap<String, Creature> creatures, HashMap<String, Treasure> treasures) {
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

        // Initialize treasures
        gameMap.treasures = treasures;

        // Put treasures into corresponding rooms
        for (String key : treasures.keySet()) {
            Treasure treasure = treasures.get(key);
            Room startingRoom = gameMap.rooms.get(treasure.currentRoomNumber());
            startingRoom.getTreasures().put(key, treasure);
        }

        return gameMap;
    }

    private static void play() throws IOException {
        moveAdventurers();
        combatAndSearch();
        moveRunner();
        if (gameMap.runner != null) {
            combatAndSearch(gameMap.runner);
        }
        moveCreatures();
        combatC();
        gameMap.setTurnCount(gameMap.getTurnCount() + 1);
        tracker.export();
    }

    private static void moveAdventurers() throws IOException {
        moveBrawler();
        moveRunner();
        moveSneaker();
        moveThief();
    }

    private static void moveBrawler() throws IOException {
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
        publisher.publish(Constants.BRAWLER_FULL_NAME + " enters room: " + gameMap.brawler.currentRoomNumber());
    }

    private static void moveRunner() throws IOException {
        if (gameMap.runner == null) {
            return;
        }
        gameMap.runner.getRoom().getAdventurers().remove(Constants.RUNNER_NAME);
        gameMap.runner.move();
        Room newRoom = gameMap.rooms.get(gameMap.runner.currentRoomNumber());
        newRoom.getAdventurers().put(Constants.RUNNER_NAME, gameMap.runner);
        gameMap.runner.setRoom(newRoom);
        publisher.publish(Constants.RUNNER_FULL_NAME + " enters room: " + gameMap.runner.currentRoomNumber());
    }

    private static void moveSneaker() throws IOException {
        if (gameMap.sneaker == null) {
            return;
        }
        gameMap.sneaker.getRoom().getAdventurers().remove(Constants.SNEAKER_NAME);
        gameMap.sneaker.move();
        Room newRoom = gameMap.rooms.get(gameMap.sneaker.currentRoomNumber());
        newRoom.getAdventurers().put(Constants.SNEAKER_NAME, gameMap.sneaker);
        gameMap.sneaker.setRoom(newRoom);
        publisher.publish(Constants.SNEAKER_FULL_NAME + " enters room: " + gameMap.sneaker.currentRoomNumber());
    }

    private static void moveThief() throws IOException {
        if (gameMap.thief == null) {
            return;
        }
        gameMap.thief.getRoom().getAdventurers().remove(Constants.THIEF_NAME);
        gameMap.thief.move();
        Room newRoom = gameMap.rooms.get(gameMap.thief.currentRoomNumber());
        newRoom.getAdventurers().put(Constants.THIEF_NAME, gameMap.thief);
        gameMap.thief.setRoom(newRoom);
        publisher.publish(Constants.THIEF_FULL_NAME + " enters room: " + gameMap.thief.currentRoomNumber());
    }

    private static void moveCreatures() throws IOException {
        for (String key : gameMap.creatures.keySet()) {
            if (key.contains("O")) {
                // Move orbiters
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
                publisher.publish(orbiter.fullName + orbiter.id + " enters room: " + orbiter.currentRoomNumber());
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
                publisher.publish(blinker.fullName + blinker.id + " enters room: " + blinker.currentRoomNumber());
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
                publisher.publish(seeker.fullName + seeker.id + " enters room: " + seeker.currentRoomNumber());
            }
        }
    }

    private static void combatAndSearch() throws IOException {
        if (gameMap.brawler != null) {
            combatAndSearch(gameMap.brawler);
        }

        if (gameMap.runner != null) {
            combatAndSearch(gameMap.runner);
        }

        if (gameMap.sneaker != null) {
            combatAndSearch(gameMap.sneaker);
        }


        if (gameMap.thief != null) {
            combatAndSearch(gameMap.thief);
        }
    }

    private static void combatC() throws IOException {
        Random random = new Random();
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

//             Let each Adventurer in this room fight with this creature
            for (String adventurerKey : adventurersInRoom.keySet()) {
                Adventurer adventurer = adventurersInRoom.get(adventurerKey);
                // StealthCombat check
                if (adventurer.combat instanceof StealthCombat) {
                    if (random.nextBoolean()) {
                        return;
                    }
                }

                Combat combat = adventurer.combat;

                int shoutResult = random.nextInt(3);

                // Shout
                if (shoutResult == 1) {
                    combat = new Shout(combat);
                } else if (shoutResult == 2) {
                    combat = new Shout(combat);
                    combat = new Shout(combat);
                }

                int danceResult = random.nextInt(3);
                // Dance
                if (danceResult == 1) {
                    combat = new Dance(combat);
                } else if (danceResult == 2) {
                    combat = new Dance(combat);
                    combat = new Dance(combat);
                }

                int jumpResult = random.nextInt(3);
                //Jump
                if (jumpResult == 1) {
                    combat = new Jump(combat);
                } else if (jumpResult == 2) {
                    combat = new Jump(combat);
                    combat = new Jump(combat);
                }

                int spinResult = random.nextInt(3);
                // Spin
                if (spinResult == 1) {
                    combat = new Spin(combat);
                } else if (spinResult == 2) {
                    combat = new Spin(combat);
                    combat = new Spin(combat);
                }

                StringBuilder builder = new StringBuilder();
                builder.append(adventurer.fullName).append(" celebrates: ");

                if (combat.combat(adventurer.armor, adventurer.gem, adventurer.sword, builder) == 0) {
                    publisher.publish(adventurer.fullName + " wins. " + creature.fullName + creature.id + " loses.");
                    currentRoom.getCreatures().remove(key);
                    iterator.remove();
                    updateCreatureCount(creature);
                    publisher.publish(creature.fullName + creature.id + " is removed.");
                    if (combat instanceof Celebrate) {
                        builder.replace(builder.length()-2, builder.length()-1, ".");
                        System.out.println(builder);
                        publisher.publish(builder.toString());
                    }
                    break;
                } else if (adventurer.combat.combat(adventurer.armor, adventurer.gem, adventurer.sword, builder) == 1) {
                    publisher.publish(adventurer.fullName + " loses. " + creature.fullName + creature.id + " wins.");
//                    if (adventurer.getDamage() < 3) {
                        adventurer.setDamage(adventurer.getDamage() + 1);
                        publisher.publish(adventurer.fullName + "'s new damage: " + adventurer.getDamage());
//                    }
                }

                checkAdventurersDamage(adventurer, currentRoom);
            }
        }
    }

    private static void combatAndSearch(Adventurer adventurer) throws IOException {
        Room currentRoom = adventurer.getRoom();
        HashMap<String, Creature> creaturesInRoom = currentRoom.getCreatures();
        HashMap<String, Treasure> treasuresInRoom = currentRoom.getTreasures();
        Random random = new Random();

        // Search
        if (creaturesInRoom.isEmpty() && !treasuresInRoom.isEmpty()) {
            if (adventurer.search.search()) {
                for (String key : treasuresInRoom.keySet()) {
                    Treasure treasure = treasuresInRoom.get(key);
                    if (treasure.name.equals(Constants.TRAP_NAME)) {
                        if (adventurer.search instanceof CarefulSearch && random.nextBoolean()) {
                            break;
                        }
                        adventurer.setDamage(adventurer.getDamage() + 1);
                        treasuresInRoom.remove(key);
                        gameMap.setTotalTreasureCount(gameMap.getTotalTreasureCount() + 1);
                        publisher.publish(treasure.name + " is found by " + adventurer.fullName + ".");
                        break;
                    } else if (adventurer.armor == null && treasure instanceof Armor) {
                        adventurer.armor = (Armor) treasure;
                        treasuresInRoom.remove(key);
                        gameMap.setTotalTreasureCount(gameMap.getTotalTreasureCount() + 1);
                        publisher.publish(treasure.name + " is found by " + adventurer.fullName + ".");
                        break;
                    } else if (adventurer.gem == null && treasure instanceof Gem) {
                        adventurer.gem = (Gem) treasure;
                        treasuresInRoom.remove(key);
                        gameMap.setTotalTreasureCount(gameMap.getTotalTreasureCount() + 1);
                        publisher.publish(treasure.name + " is found by " + adventurer.fullName + ".");
                        break;
                    } else if (treasure.name.equals(Constants.PORTAL_NAME)) {
                        // Delete adventurer in original room
                        adventurer.getRoom().getAdventurers().remove(adventurer.getName());
                        // Move adventurer into a random room
                        adventurer.portalMove();
                        // Add adventurer into new room
                        Room newRoom = gameMap.rooms.get(adventurer.currentRoomNumber());
                        newRoom.getAdventurers().put(adventurer.getName(), adventurer);
                        adventurer.setRoom(newRoom);
                        treasuresInRoom.remove(key);
                        gameMap.setTotalTreasureCount(gameMap.getTotalTreasureCount() + 1);
                        publisher.publish(treasure.name + " is found by " + adventurer.fullName + ".");
                        break;
                    } else if (adventurer.potion == null && treasure instanceof Potion) {
                        adventurer.potion = (Potion) treasure;
                        treasuresInRoom.remove(key);
                        gameMap.setTotalTreasureCount(gameMap.getTotalTreasureCount() + 1);
                        publisher.publish(treasure.name + " is found by " + adventurer.fullName + ".");
                        break;
                    } else if (adventurer.sword == null && treasure instanceof Sword) {
                        adventurer.sword = (Sword) treasure;
                        treasuresInRoom.remove(key);
                        gameMap.setTotalTreasureCount(gameMap.getTotalTreasureCount() + 1);
                        publisher.publish(treasure.name + " is found by " + adventurer.fullName + ".");
                        break;
                    } else {
                        break;
                    }
                }
            }
            // Combat
        } else {
            // Sneaker and StealthCombat have a 50% chance not to have to combat Creatures found in current Room.
            if (adventurer.getName().equals(Constants.SNEAKER_NAME) || adventurer.combat instanceof StealthCombat) {
                if (random.nextBoolean()) {
                    return;
                }
            }
            
            for (Iterator<Map.Entry<String, Creature>> iterator = creaturesInRoom.entrySet().iterator(); iterator.hasNext(); ) {
                Map.Entry<String, Creature> next = iterator.next();
                String key = next.getKey();
                Creature creature = next.getValue();

                Combat combat = adventurer.combat;

                int shoutResult = random.nextInt(3);

                // Shout
                if (shoutResult == 1) {
                    combat = new Shout(combat);
                } else if (shoutResult == 2) {
                    combat = new Shout(combat);
                    combat = new Shout(combat);
                }

                int danceResult = random.nextInt(3);
                // Dance
                if (danceResult == 1) {
                    combat = new Dance(combat);
                } else if (danceResult == 2) {
                    combat = new Dance(combat);
                    combat = new Dance(combat);
                }

                int jumpResult = random.nextInt(3);
                //Jump
                if (jumpResult == 1) {
                    combat = new Jump(combat);
                } else if (jumpResult == 2) {
                    combat = new Jump(combat);
                    combat = new Jump(combat);
                }

                int spinResult = random.nextInt(3);
                //spin
                if (spinResult == 1) {
                    combat = new Spin(combat);
                } else if (spinResult == 2) {
                    combat = new Spin(combat);
                    combat = new Spin(combat);
                }

                StringBuilder builder = new StringBuilder();
                builder.append(adventurer.fullName).append(" celebrates: ");

                if (combat.combat(adventurer.armor, adventurer.gem, adventurer.sword, builder) == 0) {
                    publisher.publish(adventurer.fullName + " wins. " + creature.fullName + creature.id + " loses.");
                    iterator.remove();
                    gameMap.creatures.remove(key);
                    updateCreatureCount(creature);
                    publisher.publish(creature.fullName + creature.id + " is removed.");
                    if (combat instanceof Celebrate) {
                        builder.replace(builder.length()-2, builder.length()-1, ".");
                        System.out.println(builder);
                        publisher.publish(builder.toString());
                    }
                } else if (adventurer.combat.combat(adventurer.armor, adventurer.gem, adventurer.sword, builder) == 1) {
                    publisher.publish(adventurer.fullName + " loses. " + creature.fullName + creature.id + " wins.");
//                    if (adventurer.getDamage() < 3) {
                        adventurer.setDamage(adventurer.getDamage() + 1);
                        publisher.publish(adventurer.fullName + "'s new damage: " + adventurer.getDamage());
//                    }
                }

                checkAdventurersDamage(adventurer, currentRoom);
            }
        }
    }


    private static void checkAdventurersDamage(Adventurer adventurer, Room currentRoom) throws IOException {
        if (adventurer.getDamage() >= 3) {
            if (adventurer.potion != null && adventurer.getDamage() - 1 < 3) {
                adventurer.potion = null;
                adventurer.setDamage(adventurer.getDamage() - 1);
                return;
            }
            currentRoom.getAdventurers().remove(adventurer.getName());
            publisher.publish(adventurer.fullName + " is removed.");
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

    private static void updateCreatureCount(Creature creature) {
        if (creature instanceof Blinker) {
            gameMap.setBlinkerCount(gameMap.getBlinkerCount() - 1);
        } else if (creature instanceof Orbiter) {
            gameMap.setOrbiterCount(gameMap.getOrbiterCount() - 1);
        } else {
            gameMap.setSeekerCount(gameMap.getSeekerCount() - 1);
        }
    }

    private static String checkIfAdventurersWin() {
        if (gameMap.getTotalTreasureCount() == 24 || gameMap.creatures.size() == 0) {
            gameMap.winner = Constants.ADVENTURER_WIN;
            return Constants.ADVENTURER_WIN;
        } else if (gameMap.brawler == null && gameMap.runner == null && gameMap.sneaker == null && gameMap.thief == null) {
            gameMap.winner = Constants.CREATURE_WIN;
            return Constants.CREATURE_WIN;
        } else {
            // No winner
            return Constants.NO_WINNER;
        }
    }
}
