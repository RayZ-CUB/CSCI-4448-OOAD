package com.rz.creature;

import com.rz.Constants;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreatureFactoryTest {

    @Test
    void getCreature_inputBlinker_returnBlinkerObject() {
        Creature actualCreature = CreatureFactory.getCreature(0, Constants.BLINKER_FULL_NAME);

        assertEquals(Blinker.class, actualCreature.getClass());
    }

    @Test
    void getCreature_inputOrbiter_returnOrbiterObject() {
        Creature actualCreature = CreatureFactory.getCreature(0, Constants.ORBITER_FULL_NAME);

        assertEquals(Orbiter.class, actualCreature.getClass());
    }

    @Test
    void getCreature_inputSeeker_returnSeekerObject() {
        Creature actualCreature = CreatureFactory.getCreature(0, Constants.SEEKER_FULL_NAME);

        assertEquals(Seeker.class, actualCreature.getClass());
    }
}