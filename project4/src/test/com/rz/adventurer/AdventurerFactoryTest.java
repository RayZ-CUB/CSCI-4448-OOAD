package com.rz.adventurer;

import com.rz.Constants;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdventurerFactoryTest {

    @Test
    void getAdventurer_inputBrawler_returnBrawlerObject() {
        Adventurer actualBrawler = AdventurerFactory.getAdventurer("test", Constants.BRAWLER_NAME);

        assertEquals(Constants.BRAWLER_NAME, actualBrawler.getType());
    }

    @Test
    void getAdventurer_inputRunner_returnRunnerObject() {
        Adventurer actualRunner = AdventurerFactory.getAdventurer("test", Constants.RUNNER_NAME);

        assertEquals(Constants.RUNNER_NAME, actualRunner.getType());
    }

    @Test
    void getAdventurer_inputSneaker_returnSneakerObject() {
        Adventurer actualSneaker = AdventurerFactory.getAdventurer("test", Constants.SNEAKER_NAME);

        assertEquals(Constants.SNEAKER_NAME, actualSneaker.getType());
    }

    @Test
    void getAdventurer_inputThief_returnThiefObject() {
        Adventurer actualThief = AdventurerFactory.getAdventurer("test", Constants.THIEF_NAME);

        assertEquals(Constants.THIEF_NAME, actualThief.getType());
    }
}