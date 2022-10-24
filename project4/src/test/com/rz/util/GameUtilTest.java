package com.rz.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameUtilTest {

    @Test
    void rollDices_execute_returnSmallerThanTwelve() {
        int result = GameUtil.rollDices();

        assertTrue(result <= 12);
    }

    @Test
    void rollDices_execute_returnBiggerThanZero() {
        int result = GameUtil.rollDices();

        assertTrue(result >= 0);
    }
}