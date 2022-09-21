package com.rz.project2.creature;

import com.rz.project2.Constants;
import com.rz.project2.map.Room;

import java.util.Random;

public class Orbiter extends Creature{
    private String direction;
    public Orbiter() {
        // Set up name
        this.setName(Constants.ORBITER_NAME);

        // Set up starting room
        Random random = new Random();   // https://stackoverflow.com/questions/363681/how-do-i-generate-random-integers-within-a-specific-range-in-java
        int z = random.nextInt(4) + 1;
        int y = random.nextInt(3);
        int x = random.nextInt(3);
        while (x == 1 && y == 1) {
            y = random.nextInt(3);
            x = random.nextInt(3);
        }
        this.setRoom(new Room(z, y, x));

        // Set up movement direction
        if (random.nextBoolean()) {
            direction = Constants.CLOCKWISE;
        } else {
            direction = Constants.COUNTERCLOCKWISE;
        }
    }

    @Override
    public void move() {
        Room room = getRoom();
        if (direction.equals(Constants.CLOCKWISE)) {
            if (room.getY() == 0 && room.getX() < 2) {
                room.setX(room.getX() + 1);
            } else if (room.getY() < 2 && room.getX() == 2) {
                room.setY(room.getY() + 1);
            } else if (room.getY() == 2 && room.getX() > 0) {
                room.setX(room.getX() - 1);
            } else {
                room.setY(room.getY() - 1);
            }
        } else {
            if (room.getY() == 0 && room.getX() > 0) {
                room.setX(room.getX() -1);
            } else if (room.getY() < 2 && room.getX() == 0) {
                room.setY(room.getY() + 1);
            } else if (room.getY() == 2 && room.getX() < 2) {
                room.setX(room.getX() + 1);
            } else {
                room.setY(room.getY() - 1);
            }
        }
    }
}
