package com.rz.adventurer;

import com.rz.map.Room;
import com.rz.skill.combat.StealthCombat;
import com.rz.skill.search.CarefulSearch;
import com.rz.treasure.Armor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AdventurerTest {
    Adventurer adventurer;
    Room currentRoom;
    Room nextRoom;

    @BeforeEach
    void init() {
        currentRoom = new Room(2,1,1);
        nextRoom = new Room(2, 0, 1);
        adventurer = new Adventurer("rz", "test", 99, new StealthCombat(), new CarefulSearch());
        adventurer.setRoom(currentRoom);
        adventurer.getRoom().getAdjacentRooms().add(nextRoom);
    }

    @Test
    void move_validRoomNumber_updatedCoordinate() {
        String nextRoomNumber = nextRoom.toString();

        adventurer.move(nextRoomNumber);

        assertAll(
                () -> assertEquals(nextRoom.getZ(), adventurer.getCoordinate()[0]),
                () -> assertEquals(nextRoom.getY(), adventurer.getCoordinate()[1]),
                () -> assertEquals(nextRoom.getX(), adventurer.getCoordinate()[2])
        );
    }

    @Test
    void move_invalidRoomNumber_throwException() {
        assertThrows(RuntimeException.class, () -> adventurer.move("0-1-1"));
        assertThrows(RuntimeException.class, () -> adventurer.move("201"));
        assertThrows(RuntimeException.class, () -> adventurer.move(""));
        assertThrows(RuntimeException.class, () -> adventurer.move(" "));
        assertThrows(RuntimeException.class, () -> adventurer.move("abc"));
    }

    @Test
    void updateTreasuresSearched_passArmor_armorFlagTrue() {
        Armor armor = new Armor(1);

        adventurer.updateTreasuresSearched(armor);

        assertEquals(Boolean.TRUE, adventurer.getTreasuresSearched()[0]);
    }
}