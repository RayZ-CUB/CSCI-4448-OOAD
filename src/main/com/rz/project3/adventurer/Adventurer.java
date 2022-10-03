package com.rz.project3.adventurer;


import com.rz.project3.map.Room;
import com.rz.project3.skill.combat.Combat;
import com.rz.project3.skill.search.Search;
import com.rz.project3.treasure.*;

import java.util.Random;


//Use encapsulation in Adventurer class, accessibility of the features in adventurer class. Some methods set to private, and need to use setter and getter to access the value of that variable.

//Also use inheritance, Brawler, Runner, Sneaker and Thief use this adventurer class as a super class.

public class Adventurer {
    private String name;
    private int damage = 0;
    public Armor armor;
    public Gem gem;
    public Portal portal;
    public Potion potion;
    public Sword sword;
    public final Combat combat;
    public final Search search;
    private Room room;  //adventurers, creatures and gameMap share same room. So, the adventures' room can have same identity(memory address) as creatures' and gameMap's
    private int z;
    private int y;
    private int x;

    public Adventurer(String name, Combat combat, Search search) {
        this.z = 0;
        this.y = 1;
        this.x = 1;
        this.name = name;
        this.combat = combat;
        this.search = search;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        if (damage < 0) {
            throw new RuntimeException("Invalid damage");
        }
        this.damage = damage;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void move() {
        Random random = new Random();

        // Case 1: When at entrance room
        if (z == 0) {
            z = 1;
            return;
        }

        // Case 2: When at center room
        if (y == 1 && x == 1) {
            switch (random.nextInt(3)) {
                // Case 2.1: Level up or down
                case 0 -> {
                    // Level 1 to Level 2 (1-1-1 -> 2-1-1)
                    if (z == 1) {
                        z = 2;
                        return;
                    }
                    // Level 4 to Level 3 (4-1-1 -> 3-1-1)
                    if (z == 4) {
                        z = 3;
                        return;
                    }
                    // Move up for either level 2 or level 3
                    if (random.nextBoolean()) {
                        z--;
                        return;
                    }
                    // Move down for either level 2 or level 3
                    z++;
                    return;
                }


                // Case 2.2: Vertically
                case 1 -> {
                    // Move North
                    if (random.nextBoolean()) {
                        y--;
                        return;
                    }
                    // Move South
                    y++;
                    return;
                }

                // Case 2.3: Horizontally
                case 2 -> {
                    // Move West
                    if (random.nextBoolean()) {
                        x--;
                        return;
                    }
                    // Move East
                    x++;
                    return;
                }
            }
        }

        // Case 3: When at outer room
        // Case 3.1: corner room
        if ((y == 0 || y == 2) && (x == 0 || x == 2)) {
            // Case 3.1.1: Northwest corner
            if (y == 0 && x == 0) {
                // Move East
                if (random.nextBoolean()) {
                    x++;
                    return;
                }
                // Move South
                y++;
                return;
            }
            // Case 3.1.2: Northeast corner
            if (y == 0) {
                // Move West
                if (random.nextBoolean()) {
                    x--;
                    return;
                }
                // Move South
                y++;
                return;
            }
            // Case 3.1.3: Southeast corner
            if (x == 2) {
                // Move West
                if (random.nextBoolean()) {
                    x--;
                    return;
                }
                // Move North
                y--;
                return;
            }
            // Case 3.1.4: Southwest corner

            // Move East
            if (random.nextBoolean()) {
                x++;
                return;
            }
            // Move North
            y--;
            return;

        }

        // Case 3.2: middle room
        // Case 3.2.1: North middle room
        if (y == 0 && x == 1) {
            switch (random.nextInt(3)) {
                // Move South
                case 0 -> {
                    y++;
                    return;
                }
                // Move West
                case 1 -> {
                    x--;
                    return;
                }

                // Move East
                case 2 -> {
                    x++;
                    return;
                }
            }
        }
        // Case 3.2.2: South middle room
        if (y == 2 && x == 1) {
            switch (random.nextInt(3)) {
                // Move North
                case 0 -> {
                    y--;
                    return;
                }
                // Move West
                case 1 -> {
                    x--;
                    return;
                }
                // Move East
                case 2 -> {
                    x++;
                    return;
                }
            }
        }
        // Case 3.2.3: West middle room
        if (y == 1 && x == 0) {
            switch (random.nextInt(3)) {
                // Move North
                case 0 -> {
                    y--;
                    return;
                }
                // Move South
                case 1 -> {
                    y++;
                    return;
                }
                // Move East
                case 2 -> {
                    x++;
                    return;
                }
            }
        }
        // Case 3.2.4: East middle room
        if (y == 1 && x == 2) {
            switch (random.nextInt(3)) {
                // Move North
                case 0 -> y--;
                // Move South
                case 1 -> y++;

                // Move West
                case 2 -> x--;
            }
        }
    }

//    public int combat() {
//        int result = combat.combat();
//        if (treasure != null) {
//            result = activateTreasure(result);
//        }
//        return result;
//    }

//    private int activateTreasure(int result) {
//        switch (treasure.name) {
//            case Constants.ARMOR_NAME -> {
//            }
//            case Constants.GEM_NAME -> {}
//            case Constants.PORTAL_NAME -> {}
//            case Constants.POTION_NAME -> {}
//            case Constants.SWORD_NAME -> {
//                return result+1;
//            }
//            case Constants.TRAP_NAME -> {}
//        }
//    }

//    public boolean search() {
//        if (rollDices() >= 10) {
//            treasureCount++;
//            return true;
//        }
//        return false;
//    }

//    public int rollDices() {
//        Random random = new Random();
//        return random.nextInt(6) + 1 + random.nextInt(6) + 1;
//    }

    public String currentRoomNumber() {
        return z + "-" + y + "-" + x;
    }
}
