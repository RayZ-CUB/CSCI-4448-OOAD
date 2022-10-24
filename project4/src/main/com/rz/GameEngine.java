package com.rz;

import com.rz.adventurer.Adventurer;
import com.rz.adventurer.AdventurerFactory;
import com.rz.adventurer.Runner;
import com.rz.command.CombatCommand;
import com.rz.command.Command;
import com.rz.command.MoveCommand;
import com.rz.command.SearchCommand;
import com.rz.creature.*;
import com.rz.map.GameMap;
import com.rz.map.Room;
import com.rz.skill.celebration.*;
import com.rz.skill.combat.Combat;
import com.rz.treasure.*;

import java.io.IOException;
import java.util.*;

public class GameEngine {
    private static GameMap gameMap;
    private static Adventurer adventurer = null;
    private static Command moveCommand;
    private static Command combatCommand;
    private static Command searchCommand;
    private static Scanner in = new Scanner(System.in);
    private static boolean flag = true;

    public static void main(String[] args) throws IOException {
        System.out.println("Welcome to RotLA by Rui Zhang.\n");
        System.out.println("--------------------------------------------------------\n");

        System.out.println("Please enter your adventurer's name:");
        String name = in.nextLine().trim();

        System.out.println("Choose adventurer type:\n" +
                "1. Brawler\n" +
                "2. Runner\n" +
                "3. Sneaker\n" +
                "4. Thief");
        switch (Integer.parseInt(in.nextLine())) {
            case 1 -> adventurer = AdventurerFactory.getAdventurer(name, Constants.BRAWLER_NAME);
            case 2 -> adventurer = AdventurerFactory.getAdventurer(name, Constants.RUNNER_NAME);
            case 3 -> adventurer = AdventurerFactory.getAdventurer(name, Constants.SNEAKER_NAME);
            case 4 -> adventurer = AdventurerFactory.getAdventurer(name, Constants.THIEF_NAME);
        }

        HashMap<String, Creature> creatures = initCreatures();
        HashMap<String, Treasure> treasures = initTreasures();
        initGameMap(adventurer, creatures, treasures);

        System.out.println(gameMap);

        moveCommand = new MoveCommand(adventurer);
        combatCommand = new CombatCommand(adventurer, in, gameMap);
        searchCommand = new SearchCommand(adventurer, gameMap);

        while (flag) {
            try {
                // Adventurer Movement
                System.out.println(gameMap);
                moveAdventure();

                if (adventurer instanceof Runner) {
                    System.out.println("Do you want to move again: \n1. Yes \n2. No");
                    if (Integer.parseInt(in.nextLine()) == 1) {
                        moveAdventure();
                    }
                }

                // Adventurer combat or search
                Room currentRoom = adventurer.getRoom();
                HashMap<String, Creature> creaturesInRoom = currentRoom.getCreatures();
                HashMap<String, Treasure> treasuresInRoom = currentRoom.getTreasures();

                // Search
                if (creaturesInRoom.isEmpty()) {
                    if (!treasuresInRoom.isEmpty()) {
                        System.out.println("Do you want to search this room:\n" + "1. Yes\n" + "2. No");
                        searchCommand.execute(in.nextLine());
                    }
                    // Combat
                } else {
                    System.out.println("1. combat\n2. move:");
                    String input = in.nextLine();
                    combatCommand.execute(input);
                }

                // Creature movement
                moveCreatures();

                // Creature combat
                combatC();

                gameMap.setTurnCount(gameMap.getTurnCount() + 1);
                checkIfAdventurersWin();
            } catch (RuntimeException re) {
                if (re.getMessage().equals(Constants.INVALID_ROOM_NUMBER)) {
                    System.out.println(re);
                }
            }

        }

    }

    private static void moveAdventure() throws IOException {
        System.out.println("Please enter the room you want to move to:");
        moveCommand.execute(in.nextLine());
        // Delete adventurer in original room
        gameMap.adventurer.getRoom().setAdventurer(null);
        // Add adventurer into new room
        Room newRoom = gameMap.rooms.get(gameMap.adventurer.currentRoomNumber());
        newRoom.setAdventurer(adventurer);
        gameMap.adventurer.setRoom(newRoom);
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
//                publisher.publish(orbiter.fullName + orbiter.id + " enters room: " + orbiter.currentRoomNumber());
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
//                publisher.publish(blinker.fullName + blinker.id + " enters room: " + blinker.currentRoomNumber());
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
//                publisher.publish(seeker.fullName + seeker.id + " enters room: " + seeker.currentRoomNumber());
            }
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

            if (currentRoom.getAdventurer() == null) {
                continue;
            }

//             Let each Adventurer in this room fight with this creature
            Adventurer adventurer = currentRoom.getAdventurer();

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
            builder.append(adventurer.getName()).append(" celebrates: ");

            if (combat.combat(adventurer.armor, adventurer.gem, adventurer.sword, builder) == 0) {
//                publisher.publish(adventurer.fullName + " wins. " + creature.fullName + creature.id + " loses.");
                currentRoom.getCreatures().remove(key);
                iterator.remove();
                updateCreatureCount(creature);
//                publisher.publish(creature.fullName + creature.id + " is removed.");
                if (combat instanceof Celebrate) {
                    builder.replace(builder.length() - 2, builder.length() - 1, ".");
                    System.out.println(builder);
//                    publisher.publish(builder.toString());
                }
                break;
            } else if (adventurer.combat.combat(adventurer.armor, adventurer.gem, adventurer.sword, builder) == 1) {
//                publisher.publish(adventurer.fullName + " loses. " + creature.fullName + creature.id + " wins.");
                adventurer.setHp(adventurer.getHp() - 1);
//                publisher.publish(adventurer.fullName + "'s new damage: " + adventurer.getDamage());
            }

            if (adventurer.getHp() <= 0) {
                currentRoom.setAdventurer(null);
//            publisher.publish(adventurer.fullName + " is removed.");
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


    private static HashMap<String, Creature> initCreatures() {
        HashMap<String, Creature> creatures = new HashMap<>();
        for (int i = 0; i < 4; i++) {
            Blinker blinker = (Blinker) CreatureFactory.getCreature(i, Constants.BLINKER_FULL_NAME);
            Orbiter orbiter = (Orbiter) CreatureFactory.getCreature(i, Constants.ORBITER_FULL_NAME);
            Seeker seeker = (Seeker) CreatureFactory.getCreature(i, Constants.SEEKER_FULL_NAME);
            creatures.put(Constants.BLINKER_NAME + i, blinker);
            creatures.put(Constants.ORBITER_NAME + i, orbiter);
            creatures.put(Constants.SEEKER_NAME + i, seeker);
        }

        return creatures;
    }

    private static HashMap<String, Treasure> initTreasures() {
        HashMap<String, Treasure> treasures = new HashMap<>();
        for (int i = 0; i < 4; i++) {
            treasures.put(Constants.ARMOR_NAME + i, new Armor(i));
            treasures.put(Constants.GEM_NAME + i, new Gem(i));
            treasures.put(Constants.PORTAL_NAME + i, new Portal(i));
            treasures.put(Constants.POTION_NAME + i, new Potion(i));
            treasures.put(Constants.SWORD_NAME + i, new Sword(i));
            treasures.put(Constants.TRAP_NAME + i, new Trap(i));
        }
        return treasures;
    }

    private static void initGameMap(Adventurer adventurer, HashMap<String, Creature> creatures, HashMap<String, Treasure> treasures) {
        gameMap = new GameMap();

        // Put adventurer into entrance room
        Room entranceRoom = gameMap.rooms.get(Constants.ENTRANCE_ROOM);
        entranceRoom.setAdventurer(adventurer);

        // Initialize turn count
        gameMap.setTurnCount(0);

        // Initialize adventure
        gameMap.adventurer = adventurer;

        // Initialize room information for adventurer
        adventurer.setRoom(entranceRoom);

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
    }

    private static void checkIfAdventurersWin() {
        if (!adventurer.currentRoomNumber().equals(Constants.ENTRANCE_ROOM)) {
            return;
        }

        int count = 0;
        for (boolean bool : adventurer.getTreasuresSearched()) {
            if (bool) {
                count++;
            }
        }

        if (count == 6 || gameMap.creatures.size() == 0) {
            gameMap.winner = adventurer.getName();
            System.out.println("Winner is " + gameMap.winner);
            flag = false;
        } else if (gameMap.adventurer == null) {
            gameMap.winner = Constants.CREATURE_WIN;
            System.out.println(gameMap.winner);
            flag = false;
        }
    }
}
