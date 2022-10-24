package com.rz.command;

import com.rz.adventurer.Adventurer;
import com.rz.creature.Blinker;
import com.rz.creature.Creature;
import com.rz.creature.Orbiter;
import com.rz.map.GameMap;
import com.rz.map.Room;
import com.rz.observer.Publisher;
import com.rz.skill.celebration.*;
import com.rz.skill.combat.Combat;
import com.rz.skill.combat.StealthCombat;

import java.io.IOException;
import java.util.*;

public class CombatCommand implements Command {
    private Adventurer adventurer;
    private Scanner in;
    private GameMap gameMap;
    private Publisher publisher;

    public CombatCommand(Adventurer adventurer, Scanner in, GameMap gameMap, Publisher publisher) {
        this.adventurer = adventurer;
        this.in = in;
        this.gameMap = gameMap;
        this.publisher = publisher;
    }

    @Override
    public void execute(String command) throws IOException {
        Random random = new Random();
        Room currentRoom = adventurer.getRoom();
        HashMap<String, Creature> creaturesInRoom = currentRoom.getCreatures();
        if (Integer.parseInt(command) == 1) {
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
                builder.append(adventurer.getName()).append(" celebrates: ");

                if (combat.combat(adventurer.armor, adventurer.gem, adventurer.sword, builder) == 0) {
                    publisher.publish(adventurer.getName() + " wins. " + creature.fullName + creature.id + " loses.");
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
                    publisher.publish(adventurer.getName() + " loses. " + creature.fullName + creature.id + " wins.");
                    if (random.nextBoolean() && adventurer.combat instanceof StealthCombat) {
                        return;
                    }
                    adventurer.setHp(adventurer.getHp() - 1);
                    publisher.publish(adventurer.getName() + "'s new hp: " + adventurer.getHp());
                }
            }
        }
        else {
            int damage = creaturesInRoom.size();
            adventurer.setHp(adventurer.getHp() - damage);
        }
        checkAdventurersDamage(adventurer, currentRoom);
    }

    private void updateCreatureCount(Creature creature) {
        if (creature instanceof Blinker) {
            gameMap.setBlinkerCount(gameMap.getBlinkerCount() - 1);
        } else if (creature instanceof Orbiter) {
            gameMap.setOrbiterCount(gameMap.getOrbiterCount() - 1);
        } else {
            gameMap.setSeekerCount(gameMap.getSeekerCount() - 1);
        }
    }

    private void checkAdventurersDamage(Adventurer adventurer, Room currentRoom) throws IOException {
        if (adventurer.getHp() <= 0) {
            currentRoom.setAdventurer(null);
            publisher.publish(adventurer.getName() + " is removed.");
            gameMap.adventurer = null;
        }
    }
}
