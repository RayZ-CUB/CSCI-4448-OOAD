package com.rz.observer;

import com.rz.Constants;
import com.rz.adventurer.Adventurer;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Tracker implements Observer {
    private static Tracker tracker;
    Adventurer adventurer;
    BufferedWriter writer;
    int turn = 0;
    // 0: Adventurer's name
    // 1: Current room number
    // 2: HP
    // 3: Treasure
    ArrayList<String> adventurerInfo = new ArrayList<>(4);

    // 0: Creature's name
    // 1: Current room number
    ArrayList<String> blinker0 = new ArrayList<>(2);
    ArrayList<String> blinker1 = new ArrayList<>(2);
    ArrayList<String> blinker2 = new ArrayList<>(2);
    ArrayList<String> blinker3 = new ArrayList<>(2);
    ArrayList<String> orbiter0 = new ArrayList<>(2);
    ArrayList<String> orbiter1 = new ArrayList<>(2);
    ArrayList<String> orbiter2 = new ArrayList<>(2);
    ArrayList<String> orbiter3 = new ArrayList<>(2);
    ArrayList<String> seeker0 = new ArrayList<>(2);
    ArrayList<String> seeker1 = new ArrayList<>(2);
    ArrayList<String> seeker2 = new ArrayList<>(2);
    ArrayList<String> seeker3 = new ArrayList<>(2);

    private Tracker(BufferedWriter writerIn, Adventurer adventurerIn) {
        writer = writerIn;
        adventurer = adventurerIn;
        adventurerInfo.add(0, adventurer.getName());
        adventurerInfo.add(1, "0-1-1");
        adventurerInfo.add(2, String.valueOf(adventurer.getHp()));
        adventurerInfo.add(3, "");

        // Blinkers
        blinker0.add(0, Constants.BLINKER_FULL_NAME);
        blinker0.add(1, "");
        blinker1.add(0, Constants.BLINKER_FULL_NAME);
        blinker1.add(1, "");
        blinker2.add(0, Constants.BLINKER_FULL_NAME);
        blinker2.add(1, "");
        blinker3.add(0, Constants.BLINKER_FULL_NAME);
        blinker3.add(1, "");

        // Orbiters
        orbiter0.add(0, Constants.ORBITER_FULL_NAME);
        orbiter0.add(1, "");
        orbiter1.add(0, Constants.ORBITER_FULL_NAME);
        orbiter1.add(1, "");
        orbiter2.add(0, Constants.ORBITER_FULL_NAME);
        orbiter2.add(1, "");
        orbiter3.add(0, Constants.ORBITER_FULL_NAME);
        orbiter3.add(1, "");

        // Sneakers
        seeker0.add(0, Constants.SEEKER_FULL_NAME);
        seeker0.add(1, "");
        seeker1.add(0, Constants.SEEKER_FULL_NAME);
        seeker1.add(1, "");
        seeker2.add(0, Constants.SEEKER_FULL_NAME);
        seeker2.add(1, "");
        seeker3.add(0, Constants.SEEKER_FULL_NAME);
        seeker3.add(1, "");
    }

    public static Tracker getInstance(BufferedWriter writerIn, Adventurer adventurer) {
        if (tracker == null) {
            tracker = new Tracker(writerIn, adventurer);
        }
        return tracker;
    }

    @Override
    public void update(String event) {
        int eventLength = event.length();
        String adventurerName = adventurer.getName();

        // Adventurer
        if (event.contains(adventurerName)) {
            if (event.contains("enters")) {
                adventurerInfo.set(1, event.substring(eventLength - 5, eventLength));
            }
            if (event.contains("new damage")) {
                adventurerInfo.set(2, event.substring(eventLength - 1, eventLength));
            }
            if (event.contains("found by")) {
                int lastIndex = event.indexOf(" is");
                String treasure = event.substring(0, lastIndex);
                if (treasure.equals("Armor") || treasure.equals("Gem") || treasure.equals("Sword")) {
                    adventurerInfo.set(3, adventurerInfo.get(3) + treasure + ", ");
                }
            }
            // Blinkers
        } else if (event.contains(Constants.BLINKER_FULL_NAME + 0)) {
            if (event.contains(Constants.BLINKER_FULL_NAME + 0 + " is removed")) {
                blinker0.set(1, "");
                return;
            }
            if (event.contains("enters")) {
                blinker0.set(1, event.substring(eventLength - 5, eventLength));
            }
        } else if (event.contains(Constants.BLINKER_FULL_NAME + 1)) {
            if (event.contains(Constants.BLINKER_FULL_NAME + 1 + " is removed")) {
                blinker1.set(1, "");
                return;
            }
            if (event.contains("enters")) {
                blinker1.set(1, event.substring(eventLength - 5, eventLength));
            }
        } else if (event.contains(Constants.BLINKER_FULL_NAME + 2)) {
            if (event.contains(Constants.BLINKER_FULL_NAME + 2 + " is removed")) {
                blinker2.set(1, "");
                return;
            }
            if (event.contains("enters")) {
                blinker2.set(1, event.substring(eventLength - 5, eventLength));
            }
        } else if (event.contains(Constants.BLINKER_FULL_NAME + 3)) {
            if (event.contains(Constants.BLINKER_FULL_NAME + 3 + " is removed")) {
                blinker3.set(1, "");
                return;
            }
            if (event.contains("enters")) {
                blinker3.set(1, event.substring(eventLength - 5, eventLength));
            }
            // Orbiters
        } else if (event.contains(Constants.ORBITER_FULL_NAME + 0)) {
            if (event.contains(Constants.ORBITER_FULL_NAME + 0 + " is removed")) {
                orbiter0.set(1, "");
                return;
            }
            if (event.contains("enters")) {
                orbiter0.set(1, event.substring(eventLength - 5, eventLength));
            }
        } else if (event.contains(Constants.ORBITER_FULL_NAME + 1)) {
            if (event.contains(Constants.ORBITER_FULL_NAME + 1 + " is removed")) {
                orbiter1.set(1, "");

                return;
            }
            if (event.contains("enters")) {
                orbiter1.set(1, event.substring(eventLength - 5, eventLength));
            }
        } else if (event.contains(Constants.ORBITER_FULL_NAME + 2)) {
            if (event.contains(Constants.ORBITER_FULL_NAME + 2 + " is removed")) {
                orbiter2.set(1, "");

                return;
            }
            if (event.contains("enters")) {
                orbiter2.set(1, event.substring(eventLength - 5, eventLength));
            }
        } else if (event.contains(Constants.ORBITER_FULL_NAME + 3)) {
            if (event.contains(Constants.ORBITER_FULL_NAME + 3 + " is removed")) {
                orbiter3.set(1, "");

                return;
            }
            if (event.contains("enters")) {
                orbiter3.set(1, event.substring(eventLength - 5, eventLength));
            }
            // Seekers
        } else if (event.contains(Constants.SEEKER_FULL_NAME + 0)) {
            if (event.contains(Constants.SEEKER_FULL_NAME + 0 + " is removed")) {
                seeker0.set(1, "");

                return;
            }
            if (event.contains("enters")) {
                seeker0.set(1, event.substring(eventLength - 5, eventLength));
            }
        } else if (event.contains(Constants.SEEKER_FULL_NAME + 1)) {
            if (event.contains(Constants.SEEKER_FULL_NAME + 1 + " is removed")) {
                seeker1.set(1, "");

                return;
            }
            if (event.contains("enters")) {
                seeker1.set(1, event.substring(eventLength - 5, eventLength));
            }
        } else if (event.contains(Constants.SEEKER_FULL_NAME + 2)) {
            if (event.contains(Constants.SEEKER_FULL_NAME + 2 + " is removed")) {
                seeker2.set(1, "");

                return;
            }
            if (event.contains("enters")) {
                seeker2.set(1, event.substring(eventLength - 5, eventLength));
            }
        } else if (event.contains(Constants.SEEKER_FULL_NAME + 3)) {
            if (event.contains(Constants.SEEKER_FULL_NAME + 3 + " is removed")) {
                seeker3.set(1, "");

                return;
            }
            if (event.contains("enters")) {
                seeker3.set(1, event.substring(eventLength - 5, eventLength));
            }
        }
    }

    public void export() throws IOException {
        turn++;
        int lastIndex;
        String adventurerTreasure = "";
        if (!adventurerInfo.get(3).isBlank()) {
            lastIndex = adventurerInfo.get(3).lastIndexOf(", ");
            adventurerTreasure = adventurerInfo.get(3).substring(0, lastIndex);
        }


        StringBuilder output = new StringBuilder();
        output.append("Tracker: Turn ").append(turn).append("\n\n")
                .append("Adventurer").append("\t\t\t\t")
                .append("Room").append("\t\t\t\t")
                .append("HP").append("\t\t\t\t")
                .append("Treasure").append("\n")
                .append(adventurerInfo.get(0)).append("\t\t\t\t\t")
                .append(adventurerInfo.get(1)).append("\t\t\t\t\t")
                .append(adventurerInfo.get(2)).append("\t\t\t")
                .append(adventurerTreasure).append("\n\n")
                .append("Creatures").append("\t\t\t\t")
                .append("Room").append("\n");
        if (!blinker0.get(1).isEmpty()) {
            output.append(blinker0.get(0)).append("\t\t\t\t\t")
                    .append(blinker0.get(1)).append("\n");
        }
        if (!blinker1.get(1).isEmpty()) {
            output.append(blinker1.get(0)).append("\t\t\t\t\t")
                    .append(blinker1.get(1)).append("\n");
        }
        if (!blinker2.get(1).isEmpty()) {
            output.append(blinker2.get(0)).append("\t\t\t\t\t")
                    .append(blinker2.get(1)).append("\n");
        }
        if (!blinker3.get(1).isEmpty()) {
            output.append(blinker3.get(0)).append("\t\t\t\t\t")
                    .append(blinker3.get(1)).append("\n");
        }
        if (!orbiter0.get(1).isEmpty()) {
            output.append(orbiter0.get(0)).append("\t\t\t\t\t")
                    .append(orbiter0.get(1)).append("\n");
        }
        if (!orbiter1.get(1).isEmpty()) {
            output.append(orbiter1.get(0)).append("\t\t\t\t\t")
                    .append(orbiter1.get(1)).append("\n");
        }
        if (!orbiter2.get(1).isEmpty()) {
            output.append(orbiter2.get(0)).append("\t\t\t\t\t")
                    .append(orbiter2.get(1)).append("\n");
        }
        if (!orbiter3.get(1).isEmpty()) {
            output.append(orbiter3.get(0)).append("\t\t\t\t\t")
                    .append(orbiter3.get(1)).append("\n");
        }
        if (!seeker0.get(1).isEmpty()) {
            output.append(seeker0.get(0)).append("\t\t\t\t\t")
                    .append(seeker0.get(1)).append("\n");
        }
        if (!seeker1.get(1).isEmpty()) {
            output.append(seeker1.get(0)).append("\t\t\t\t\t")
                    .append(seeker1.get(1)).append("\n");
        }
        if (!seeker2.get(1).isEmpty()) {
            output.append(seeker2.get(0)).append("\t\t\t\t\t")
                    .append(seeker2.get(1)).append("\n");
        }
        if (!seeker3.get(1).isEmpty()) {
            output.append(seeker3.get(0)).append("\t\t\t\t\t")
                    .append(seeker3.get(1)).append("\n");
        }
        output.append("\n\n");
        writer.write(output.toString());
    }
}
