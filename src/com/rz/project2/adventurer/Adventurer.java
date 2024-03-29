package com.rz.project2.adventurer;

import com.rz.project2.map.Room;

import java.util.Random;

public class Adventurer {
    private String name;
    private int damage = 0;
    private int treasureCount;
    private Room room;
    private int z;
    private int y;
    private int x;


    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        if (damage < 0 || damage > 3) {
            throw new RuntimeException("Invalid damage");
        }
        this.damage = damage;
    }

    public int getTreasureCount() {
        return treasureCount;
    }

    public void setTreasureCount(int treasureCount) {
        if (treasureCount < 0 || treasureCount > 10) {
            throw new RuntimeException("Invalid treasure count");
        }
        this.treasureCount = treasureCount;
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

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        if (z < 0 || z > 4) {
            System.out.println("Invalid level index");
        }
        this.z = z;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        if (y < 0 || y > 2) {
            System.out.println("Invalid vertical index");
        }
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        if (x < 0 || x > 2) {
            System.out.println("Invalid horizontal index");
        }
        this.x = x;
    }

    // TODO: leave facility
    public void move() {
        Random random = new Random();

        // Case 1: When at entrance room
        if (room.getZ() == 0) {
            y = room.getY();
            x = room.getX();
            z = 1;
            return;
        }

        // Case 2: When at center room
        if (room.getY() == 1 && room.getX() == 1) {
            switch (random.nextInt(3)) {
                // Case 2.1: Level up or down
                case 0 -> {
                    y = room.getY();
                    x = room.getX();

                    // Level 1 to Level 2 (1-1-1 -> 2-1-1)
                    if (room.getZ() == 1) {
                        z = 2;
                        return;
                    }

                    // Level 4 to Level 3 (4-1-1 -> 3-1-1)
                    if (room.getZ() == 4) {
                        z = 3;
                        return;
                    }

                    // Move up for either level 2 or level 3
                    if (random.nextBoolean()) {
                        z = room.getZ() - 1;
                        return;
                    }

                    // Move down for either level 2 or level 3
                    z = room.getZ() + 1;
                    return;
                }


                // Case 2.2: Vertically
                case 1 -> {
                    z = room.getZ();
                    x = room.getX();
                    // Move North
                    if (random.nextBoolean()) {
                        y = room.getY() - 1;
                        return;
                    }

                    // Move South
                    y = room.getY() + 1;
                    return;
                }

                // Case 2.3: Horizontally
                case 2 -> {
                    z = room.getZ();
                    y = room.getY();
                    // Move West
                    if (random.nextBoolean()) {
                        x = room.getX() - 1;
                        return;
                    }

                    // Move East
                    x = room.getX() + 1;
                    return;
                }
            }
        }

        z = room.getZ();

        // Case 3: When at outer room
        // Case 3.1: corner room
        if ((room.getY() == 0 || room.getY() == 2) && (room.getX() == 0 || room.getX() == 2)) {
            // Case 3.1.1: Northwest corner
            if (room.getY() == 0 && room.getX() == 0) {
                // Move East
                if (random.nextBoolean()) {
                    y = room.getY();
                    x = room.getX() + 1;
                    return;
                }

                // Move South
                x = room.getX();
                y = room.getY() + 1;
                return;
            }
            // Case 3.1.2: Northeast corner
            if (room.getY() == 0 && room.getX() == 2) {
                // Move West
                if (random.nextBoolean()) {
                    y = room.getY();
                    x = room.getX() - 1;
                    return;
                }

                // Move South
                x = room.getX();
                y = room.getY() + 1;
                return;
            }
            // Case 3.1.3: Southeast corner
            if (room.getY() == 2 && room.getX() == 2) {
                // Move West
                if (random.nextBoolean()) {
                    y = room.getY();
                    x = room.getX() - 1;
                    return;
                }

                // Move North
                x = room.getX();
                y = room.getY() - 1;
                return;
            }
            // Case 3.1.4: Southwest corner
            if (room.getY() == 2 && room.getX() == 0) {
                // Move East
                if (random.nextBoolean()) {
                    y = room.getY();
                    x = room.getX() + 1;
                    return;
                }

                // Move North
                x = room.getX();
                y = room.getY() - 1;
                return;
            }
        }

        // Case 3.2: middle room
        // Case 3.2.1: North middle room
        if (room.getY() == 0 && room.getX() == 1) {
            switch (random.nextInt(3)) {
                // Move South
                case 0 -> {
                    x = room.getX();
                    y = room.getY() + 1;
                    return;
                }

                // Move West
                case 1 -> {
                    y = room.getY();
                    x = room.getX() - 1;
                    return;
                }

                // Move East
                case 2 -> {
                    y = room.getY();
                    x = room.getX() + 1;
                    return;
                }
            }
        }
        // Case 3.2.2: South middle room
        if (room.getY() == 2 && room.getX() == 1) {
            switch (random.nextInt(3)) {
                // Move North
                case 0 -> {
                    x = room.getX();
                    y = room.getY() - 1;
                    return;
                }

                // Move West
                case 1 -> {
                    y = room.getY();
                    x = room.getX() - 1;
                    return;
                }

                // Move East
                case 2 -> {
                    y = room.getY();
                    x = room.getX() + 1;
                    return;
                }
            }
        }
        // Case 3.2.3: West middle room
        if (room.getY() == 1 && room.getX() == 0) {
            switch (random.nextInt(3)) {
                // Move North
                case 0 -> {
                    x = room.getX();
                    y = room.getY() - 1;
                    return;
                }

                // Move South
                case 1 -> {
                    x = room.getX();
                    y = room.getY() + 1;
                    return;
                }

                // Move East
                case 2 -> {
                    y = room.getY();
                    x = room.getX() + 1;
                    return;
                }
            }
        }
        // Case 3.2.4: East middle room
        if (room.getY() == 1 && room.getX() == 2) {
            switch (random.nextInt(3)) {
                // Move North
                case 0 -> {
                    x = room.getX();
                    y = room.getY() - 1;
                }

                // Move South
                case 1 -> {
                    x = room.getX();
                    y = room.getY() + 1;
                }

                // Move West
                case 2 -> {
                    y = room.getY();
                    x = room.getX() - 1;
                }
            }
        }
    }
//    public void move() {
//        Random random = new Random();
//
//        // Case 1: When at entrance room
//        if (room.getZ() == 0) {
//            room.setZ(1);
//            return;
//        }
//
//        // Case 2: When at center room
//        if (room.getY() == 1 && room.getX() == 1) {
//            switch (random.nextInt(3)) {
//                // Case 2.1: Level up or down
//                case 0 -> {
//                    // Level 1 to Level 2 (1-1-1 -> 2-1-1)
//                    if (room.getZ() == 1) {
//                        room.setZ(2);
//                        return;
//                    }
//
//                    // Level 4 to Level 3 (4-1-1 -> 3-1-1)
//                    if (room.getZ() == 4) {
//                        room.setZ(3);
//                        return;
//                    }
//
//                    // Move up for either level 2 or level 3
//                    if (random.nextBoolean()) {
//                        room.setZ(room.getZ() - 1);
//                        return;
//                    }
//
//                    // Move down for either level 2 or level 3
//                    room.setZ(room.getZ() + 1);
//                    return;
//                }
//
//
//                // Case 2.2: Vertically
//                case 1 -> {
//                    // Move North
//                    if (random.nextBoolean()) {
//                        room.setY(room.getY() - 1);
//                        return;
//                    }
//
//                    // Move South
//                    room.setY(room.getY() + 1);
//                    return;
//                }
//
//                // Case 2.3: Horizontally
//                case 2 -> {
//                    // Move West
//                    if (random.nextBoolean()) {
//                        room.setX(room.getX() - 1);
//                        return;
//                    }
//
//                    // Move East
//                    room.setX(room.getX() + 1);
//                    return;
//                }
//            }
//        }
//
//
//        // Case 3: When at outer room
//        // Case 3.1: corner room
//        if ((room.getY() == 0 || room.getY() == 2) && (room.getX() == 0 || room.getX() == 2)) {
//            // Case 3.1.1: Northwest corner
//            if (room.getY() == 0 && room.getX() == 0) {
//                // Move East
//                if (random.nextBoolean()) {
//                    room.setX(room.getX() + 1);
//                    return;
//                }
//
//                // Move South
//                room.setY(room.getY() + 1);
//                return;
//            }
//            // Case 3.1.2: Northeast corner
//            if (room.getY() == 0 && room.getX() == 2) {
//                // Move West
//                if (random.nextBoolean()) {
//                    room.setX(room.getX() - 1);
//                    return;
//                }
//
//                // Move South
//                room.setY(room.getY() + 1);
//                return;
//            }
//            // Case 3.1.3: Southeast corner
//            if (room.getY() == 2 && room.getX() == 2) {
//                // Move West
//                if (random.nextBoolean()) {
//                    room.setX(room.getX() - 1);
//                    return;
//                }
//
//                // Move North
//                room.setY(room.getY() - 1);
//                return;
//            }
//            // Case 3.1.4: Southwest corner
//            if (room.getY() == 2 && room.getX() == 0) {
//                // Move East
//                if (random.nextBoolean()) {
//                    room.setX(room.getX() + 1);
//                    return;
//                }
//
//                // Move North
//                room.setY(room.getY() - 1);
//                return;
//            }
//        }
//
//        // Case 3.2: middle room
//        // Case 3.2.1: North middle room
//        if (room.getY() == 0 && room.getX() == 1) {
//            switch (random.nextInt(3)) {
//                // Move South
//                case 0 -> {
//                    room.setY(room.getY() + 1);
//                    return;
//                }
//
//                // Move West
//                case 1 -> {
//                    room.setX(room.getX() - 1);
//                    return;
//                }
//
//                // Move East
//                case 2 -> {
//                    room.setX(room.getX() + 1);
//                    return;
//                }
//            }
//        }
//        // Case 3.2.2: South middle room
//        if (room.getY() == 2 && room.getX() == 1) {
//            switch (random.nextInt(3)) {
//                // Move North
//                case 0 -> {
//                    room.setY(room.getY() - 1);
//                    return;
//                }
//
//                // Move West
//                case 1 -> {
//                    room.setX(room.getX() - 1);
//                    return;
//                }
//
//                // Move East
//                case 2 -> {
//                    room.setX(room.getX() + 1);
//                    return;
//                }
//            }
//        }
//        // Case 3.2.3: West middle room
//        if (room.getY() == 1 && room.getX() == 0) {
//            switch (random.nextInt(3)) {
//                // Move North
//                case 0 -> {
//                    room.setY(room.getY() - 1);
//                    return;
//                }
//
//                // Move South
//                case 1 -> {
//                    room.setY(room.getY() + 1);
//                    return;
//                }
//
//                // Move East
//                case 2 -> {
//                    room.setX(room.getX() + 1);
//                    return;
//                }
//            }
//        }
//        // Case 3.2.4: East middle room
//        if (room.getY() == 1 && room.getX() == 2) {
//            switch (random.nextInt(3)) {
//                // Move North
//                case 0 -> room.setY(room.getY() - 1);
//
//                // Move South
//                case 1 -> room.setY(room.getY() + 1);
//
//                // Move West
//                case 2 -> room.setX(room.getX() - 1);
//            }
//        }
//    }

    public int rollDices() {
        Random random = new Random();
        return random.nextInt(6) + 1 + random.nextInt(6) + 1;
    }
    public int attack() {
        return rollDices();
    }
    public int search() {
        return rollDices();
    }
}
