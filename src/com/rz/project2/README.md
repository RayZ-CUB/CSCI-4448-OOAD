# Project2: RotLA 8-)
------------------------------

## Game Description
+ The four Adventurers enter the maze to kill monsters or collect treasures. They can start the game directly without user operation.
  - Adventruers: Brawler, Runner, Sneaker, Thief (Each of them has 3 hp)
  - Creatures: Blinker, Orbiter, Seeker (4 for each type, only 1 hp for each)
  - The game also can be end by adventurers collect 10 treasures


## environment for the project
* Java 1.8+
* IDEA 2022
* SDK: openjdk-18
* Language level: SDK default(18






### Deployment Steps
1. Add Java to system environment variables
2. Configure Java in IDEA
3. Run IDEA

### File structure directory

> GameEngine.java --> run the program


> Constants.java

> map
```
GameMap.java
Room.java
```
> adventurers
```
Adventurer.java
Brawler.java
Runner.java
Sneaker.java
Thief.java
```
> creatures
```
Creature.java
Blinker.java
Orbiter.java
Seeker.java
```

## instructions
+ Only click run

## UML update
+ We have subdivided UML in more detail, and defined subclasses and superclasses, private and public, as well as various returns and outputs contained in each class in more detail. For the overall changes, we have written sub classes for each Adventurer and each Creature. We include the methods of map printing, victory conditions, and creations count, adventurers count, and treatment count, fight, and search in the same file GameEngine.java.

### Author：
+ Rui Zhang
+ Congke Zhao





