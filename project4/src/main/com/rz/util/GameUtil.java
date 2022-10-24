package com.rz.util;

import java.util.Random;

public class GameUtil {
    public static int rollDices() {
        Random random = new Random();
        return random.nextInt(6) + 1 + random.nextInt(6) + 1;
    }
}
