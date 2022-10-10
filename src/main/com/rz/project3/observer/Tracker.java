package com.rz.project3.observer;

import com.rz.project3.Constants;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Tracker implements Observer {
    BufferedWriter writer;
    int turn = 0;
    int totalActiveCreatures = 12;
    // 0: Adventurer's name
    // 1: Current room number
    // 2: Damage
    // 3: Treasure
    ArrayList<String> brawler = new ArrayList<>(4);
    ArrayList<String> sneaker = new ArrayList<>(4);
    ArrayList<String> runner = new ArrayList<>(4);
    ArrayList<String> thief = new ArrayList<>(4);

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

    public Tracker(BufferedWriter writer) {
        this.writer = writer;
        brawler.add(0, Constants.BRAWLER_FULL_NAME);
        brawler.add(1, "0-1-1");
        brawler.add(2, "0");
        brawler.add(3, "");
        sneaker.add(0, Constants.SNEAKER_FULL_NAME);
        sneaker.add(1, "0-1-1");
        sneaker.add(2, "0");
        sneaker.add(3, "");
        runner.add(0, Constants.RUNNER_FULL_NAME);
        runner.add(1, "0-1-1");
        runner.add(2, "0");
        runner.add(3, "");
        thief.add(0, Constants.THIEF_FULL_NAME);
        thief.add(1, "0-1-1");
        thief.add(2, "0");
        thief.add(3, "");

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

    @Override
    public void update(String event) {
        int eventLength = event.length();

        // Brawler
        if (event.contains(Constants.BRAWLER_FULL_NAME)) {
            if (event.contains("enters")) {
                brawler.set(1, event.substring(eventLength - 5, eventLength));
            }
            if (event.contains("new damage")) {
                brawler.set(2, event.substring(eventLength - 1, eventLength));
            }
            if (event.contains("found by")) {
                int lastIndex = event.indexOf(" is");
                String treasure = event.substring(0, lastIndex);
                if (treasure.equals("Armor") || treasure.equals("Gem") || treasure.equals("Sword")) {
                    brawler.set(3, brawler.get(3) + treasure + ", ");
                }
            }
            // Sneaker
        } else if (event.contains(Constants.SNEAKER_FULL_NAME)) {
            if (event.contains("enters")) {
                sneaker.set(1, event.substring(eventLength - 5, eventLength));
            }
            if (event.contains("new damage")) {
                sneaker.set(2, event.substring(eventLength - 1, eventLength));
            }
            if (event.contains("found by")) {
                int lastIndex = event.indexOf(" is");
                String treasure = event.substring(0, lastIndex);
                if (treasure.equals("Armor") || treasure.equals("Gem") || treasure.equals("Sword")) {
                    sneaker.set(3, sneaker.get(3) + treasure + ", ");
                }
            }
            // Runner
        } else if (event.contains(Constants.RUNNER_FULL_NAME)) {
            if (event.contains("enters")) {
                runner.set(1, event.substring(eventLength - 5, eventLength));
            }
            if (event.contains("new damage")) {
                runner.set(2, event.substring(eventLength - 1, eventLength));
            }
            if (event.contains("found by")) {
                int lastIndex = event.indexOf(" is");
                String treasure = event.substring(0, lastIndex);
                if (treasure.equals("Armor") || treasure.equals("Gem") || treasure.equals("Sword")) {
                    runner.set(3, runner.get(3) + treasure + ", ");
                }
            }
            // Thief
        } else if (event.contains(Constants.THIEF_FULL_NAME)) {
            if (event.contains("enters")) {
                thief.set(1, event.substring(eventLength - 5, eventLength));
            }
            if (event.contains("new damage")) {
                thief.set(2, event.substring(eventLength - 1, eventLength));
            }
            if (event.contains("found by")) {
                int lastIndex = event.indexOf(" is");
                String treasure = event.substring(0, lastIndex);
                if (treasure.equals("Armor") || treasure.equals("Gem") || treasure.equals("Sword")) {
                    thief.set(3, thief.get(3) + treasure + ", ");
                }
            }
            // Blinkers
        } else if (event.contains(Constants.BLINKER_FULL_NAME + 0)) {
            if (event.contains(Constants.BLINKER_FULL_NAME + 0 + " is removed")) {
                blinker0.set(1, "");
                totalActiveCreatures--;
                return;
            }
            if (event.contains("enters")) {
                blinker0.set(1, event.substring(eventLength - 5, eventLength));
            }
        } else if (event.contains(Constants.BLINKER_FULL_NAME + 1)) {
            if (event.contains(Constants.BLINKER_FULL_NAME + 1 + " is removed")) {
                blinker1.set(1, "");
                totalActiveCreatures--;
                return;
            }
            if (event.contains("enters")) {
                blinker1.set(1, event.substring(eventLength - 5, eventLength));
            }
        } else if (event.contains(Constants.BLINKER_FULL_NAME + 2)) {
            if (event.contains(Constants.BLINKER_FULL_NAME + 2 + " is removed")) {
                blinker2.set(1, "");
                totalActiveCreatures--;
                return;
            }
            if (event.contains("enters")) {
                blinker2.set(1, event.substring(eventLength - 5, eventLength));
            }
        } else if (event.contains(Constants.BLINKER_FULL_NAME + 3)) {
            if (event.contains(Constants.BLINKER_FULL_NAME + 3 + " is removed")) {
                blinker3.set(1, "");
                totalActiveCreatures--;
                return;
            }
            if (event.contains("enters")) {
                blinker3.set(1, event.substring(eventLength - 5, eventLength));
            }
            // Orbiters
        } else if (event.contains(Constants.ORBITER_FULL_NAME + 0)) {
            if (event.contains(Constants.ORBITER_FULL_NAME + 0 + " is removed")) {
                orbiter0.set(1, "");
                totalActiveCreatures--;
                return;
            }
            if (event.contains("enters")) {
                orbiter0.set(1, event.substring(eventLength - 5, eventLength));
            }
        } else if (event.contains(Constants.ORBITER_FULL_NAME + 1)) {
            if (event.contains(Constants.ORBITER_FULL_NAME + 1 + " is removed")) {
                orbiter1.set(1, "");
                totalActiveCreatures--;
                return;
            }
            if (event.contains("enters")) {
                orbiter1.set(1, event.substring(eventLength - 5, eventLength));
            }
        } else if (event.contains(Constants.ORBITER_FULL_NAME + 2)) {
            if (event.contains(Constants.ORBITER_FULL_NAME + 2 + " is removed")) {
                orbiter2.set(1, "");
                totalActiveCreatures--;
                return;
            }
            if (event.contains("enters")) {
                orbiter2.set(1, event.substring(eventLength - 5, eventLength));
            }
        } else if (event.contains(Constants.ORBITER_FULL_NAME + 3)) {
            if (event.contains(Constants.ORBITER_FULL_NAME + 3 + " is removed")) {
                orbiter3.set(1, "");
                totalActiveCreatures--;
                return;
            }
            if (event.contains("enters")) {
                orbiter3.set(1, event.substring(eventLength - 5, eventLength));
            }
            // Seekers
        } else if (event.contains(Constants.SEEKER_FULL_NAME + 0)) {
            if (event.contains(Constants.SEEKER_FULL_NAME + 0 + " is removed")) {
                seeker0.set(1, "");
                totalActiveCreatures--;
                return;
            }
            if (event.contains("enters")) {
                seeker0.set(1, event.substring(eventLength - 5, eventLength));
            }
        } else if (event.contains(Constants.SEEKER_FULL_NAME + 1)) {
            if (event.contains(Constants.SEEKER_FULL_NAME + 1 + " is removed")) {
                seeker1.set(1, "");
                totalActiveCreatures--;
                return;
            }
            if (event.contains("enters")) {
                seeker1.set(1, event.substring(eventLength - 5, eventLength));
            }
        } else if (event.contains(Constants.SEEKER_FULL_NAME + 2)) {
            if (event.contains(Constants.SEEKER_FULL_NAME + 2 + " is removed")) {
                seeker2.set(1, "");
                totalActiveCreatures--;
                return;
            }
            if (event.contains("enters")) {
                seeker2.set(1, event.substring(eventLength - 5, eventLength));
            }
        } else if (event.contains(Constants.SEEKER_FULL_NAME + 3)) {
            if (event.contains(Constants.SEEKER_FULL_NAME + 3 + " is removed")) {
                seeker3.set(1, "");
                totalActiveCreatures--;
                return;
            }
            if (event.contains("enters")) {
                seeker3.set(1, event.substring(eventLength - 5, eventLength));
            }
        }
    }

    public void export() throws IOException {
        turn ++;
        int lastIndex;
        String brawlerTreasure = "";
        String sneakerTreasure = "";
        String runnerTreasure = "";
        String thiefTreasure = "";
        if (!brawler.get(3).isBlank()) {
            lastIndex = brawler.get(3).lastIndexOf(", ");
            brawlerTreasure = brawler.get(3).substring(0, lastIndex);
        }
        if (!sneaker.get(3).isBlank()) {
            lastIndex = sneaker.get(3).lastIndexOf(", ");
            sneakerTreasure = sneaker.get(3).substring(0, lastIndex);
        }
        if (!runner.get(3).isBlank()) {
            lastIndex = runner.get(3).lastIndexOf(", ");
            runnerTreasure = runner.get(3).substring(0, lastIndex);
        }
        if (!thief.get(3).isBlank()) {
            lastIndex = thief.get(3).lastIndexOf(", ");
            thiefTreasure = thief.get(3).substring(0, lastIndex);
        }

        StringBuilder output = new StringBuilder();
        output.append("Tracker: Turn ").append(turn).append("\n\n")
                .append("Total Active Adventurers: ").append(totalActiveAdventurers()).append("\n\n")
                .append("Adventurers").append("\t\t\t\t")
                .append("Room").append("\t\t\t\t")
                .append("Damage").append("\t\t\t\t")
                .append("Treasure").append("\n")
                .append(brawler.get(0)).append("\t\t\t\t\t")
                .append(brawler.get(1)).append("\t\t\t\t\t")
                .append(brawler.get(2)).append("\t\t\t\t")
                .append(brawlerTreasure).append("\n")
                .append(sneaker.get(0)).append("\t\t\t\t\t")
                .append(sneaker.get(1)).append("\t\t\t\t\t")
                .append(sneaker.get(2)).append("\t\t\t\t")
                .append(sneakerTreasure).append("\n")
                .append(runner.get(0)).append("\t\t\t\t\t")
                .append(runner.get(1)).append("\t\t\t\t\t")
                .append(runner.get(2)).append("\t\t\t\t")
                .append(runnerTreasure).append("\n")
                .append(thief.get(0)).append("\t\t\t\t\t")
                .append(thief.get(1)).append("\t\t\t\t\t")
                .append(thief.get(2)).append("\t\t\t\t")
                .append(thiefTreasure).append("\n\n")
                .append("Total Active Creatures: ").append(totalActiveCreatures).append("\n\n")
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

    private int totalActiveAdventurers() {
        int count = 0;
        if (Integer.parseInt(brawler.get(2)) < 3) {
            count++;
        }
        if (Integer.parseInt(sneaker.get(2)) < 3) {
            count++;
        }
        if (Integer.parseInt(runner.get(2)) < 3) {
            count++;
        }
        if (Integer.parseInt(thief.get(2)) < 3) {
            count++;
        }
        return count;
    }
}
