package com.rz.command;

import com.rz.adventurer.Adventurer;

public class MoveCommand implements Command{
    private Adventurer adventurer;

    public MoveCommand(Adventurer adventurer) {
        this.adventurer = adventurer;
    }

    public Adventurer getAdventurer() {
        return adventurer;
    }

    public void setAdventurer(Adventurer adventurer) {
        this.adventurer = adventurer;
    }

    @Override
    public void execute(String nextRoomNumber) {
        adventurer.move(nextRoomNumber);
    }
}
