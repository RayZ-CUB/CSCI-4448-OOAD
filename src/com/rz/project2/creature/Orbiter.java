package com.rz.project2.creature;

import com.rz.project2.map.Room;

import java.util.Random;

public class Orbiter extends Creature{
    public Orbiter() {
        this.setName("O");

        Random random = new Random();   // https://stackoverflow.com/questions/363681/how-do-i-generate-random-integers-within-a-specific-range-in-java
        int z = random.nextInt(4) + 1;
        int y = random.nextInt(3);
        int x = random.nextInt(3);
        while (x == 1 && y == 1) {
            y = random.nextInt(3);
            x = random.nextInt(3);
        }
        this.setRoom(new Room(z, y, x));
    }
}
