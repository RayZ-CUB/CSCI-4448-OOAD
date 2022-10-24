package com.rz.command;

import com.rz.Constants;
import com.rz.adventurer.Adventurer;
import com.rz.map.GameMap;
import com.rz.map.Room;
import com.rz.observer.Publisher;
import com.rz.skill.search.CarefulSearch;
import com.rz.treasure.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

public class SearchCommand implements Command {
    Adventurer adventurer;
    GameMap gameMap;
    Publisher publisher;

    public SearchCommand(Adventurer adventurer, GameMap gameMap, Publisher publisher) {
        this.adventurer = adventurer;
        this.gameMap = gameMap;
        this.publisher = publisher;
    }

    @Override
    public void execute(String command) throws IOException {
        if (Integer.parseInt(command) == 1) {
            Room currentRoom = adventurer.getRoom();
            HashMap<String, Treasure> treasuresInRoom = currentRoom.getTreasures();
            Random random = new Random();

            if (adventurer.search.search()) {
                for (String key : treasuresInRoom.keySet()) {
                    Treasure treasure = treasuresInRoom.get(key);
                    if (treasure.name.equals(Constants.TRAP_NAME)) {
                        if (adventurer.search instanceof CarefulSearch && random.nextBoolean()) {
                            break;
                        }
                        adventurer.setHp(adventurer.getHp() - 1);
                        treasuresInRoom.remove(key);
                        gameMap.treasures.remove(key);
                        adventurer.updateTreasuresSearched(treasure);
                        publisher.publish(treasure.name + " is found by " + adventurer.getName() + ".");
                        break;
                    } else if (adventurer.armor == null && treasure instanceof Armor) {
                        adventurer.armor = (Armor) treasure;
                        treasuresInRoom.remove(key);
                        gameMap.treasures.remove(key);
                        adventurer.updateTreasuresSearched(treasure);
                        publisher.publish(treasure.name + " is found by " + adventurer.getName() + ".");
                        break;
                    } else if (adventurer.gem == null && treasure instanceof Gem) {
                        adventurer.gem = (Gem) treasure;
                        treasuresInRoom.remove(key);
                        gameMap.treasures.remove(key);
                        adventurer.updateTreasuresSearched(treasure);
                        publisher.publish(treasure.name + " is found by " + adventurer.getName() + ".");
                        break;
                    } else if (treasure.name.equals(Constants.PORTAL_NAME)) {
                        // Delete adventurer in original room
                        adventurer.getRoom().setAdventurer(null);
                        // Move adventurer into a random room
                        adventurer.portalMove();
                        // Add adventurer into new room
                        Room newRoom = gameMap.rooms.get(adventurer.currentRoomNumber());
                        newRoom.setAdventurer(adventurer);
                        adventurer.setRoom(newRoom);
                        treasuresInRoom.remove(key);
                        gameMap.treasures.remove(key);
                        adventurer.updateTreasuresSearched(treasure);
                        publisher.publish(treasure.name + " is found by " + adventurer.getName() + ".");
                        break;
                    } else if (treasure instanceof Potion) {
                        adventurer.setHp(adventurer.getHp() + 1);
                        treasuresInRoom.remove(key);
                        gameMap.treasures.remove(key);
                        adventurer.updateTreasuresSearched(treasure);
                        publisher.publish(treasure.name + " is found by " + adventurer.getName() + ".");
                        break;
                    } else if (adventurer.sword == null && treasure instanceof Sword) {
                        adventurer.sword = (Sword) treasure;
                        treasuresInRoom.remove(key);
                        gameMap.treasures.remove(key);
                        adventurer.updateTreasuresSearched(treasure);
                        publisher.publish(treasure.name + " is found by " + adventurer.getName() + ".");
                        break;
                    }
                }
            }
        }
    }
}
