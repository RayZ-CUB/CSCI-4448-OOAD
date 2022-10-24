package com.rz.treasure;

import java.util.Random;

public class Treasure {
    public String name;
    private int id;
    private int[] coordinate;

    public Treasure(String name, int id) {
        this.name = name;
        this.id = id;
        coordinate = setCoordinate();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int[] getCoordinate() {
        return coordinate;
    }

    private int[] setCoordinate() {
        Random random = new Random();
        int[] ints = new int[3];
        int z = random.nextInt(4) + 1;
        int y = random.nextInt(3);
        int x = random.nextInt(3);
        ints[0] = z;
        ints[1] = y;
        ints[2] = x;
        return ints;
    }

    public String currentRoomNumber() {
        return coordinate[0] + "-" + coordinate[1] + "-" + coordinate[2];
    }
}
