package com.game.model.Characters.subclasses;
import com.game.model.Characters.Character;
import com.game.model.Items.subclasses.Weapon;

/**
 * Lead Author(s):
 * @author Jesse Marino
 * 
 * Version/date: 10-25-2024
 * 
 * Responsibilities of class:
 * Represents an animal or enemy that can be in the game/World and extends Character
 */
public class Animal extends Character {
    // Instance variables
    private static Weapon weapon = new Weapon("Bite", "A bite from an animal", 0, 0, 0, "Common", 15);
    private boolean rabid;

    /**
     * Constructor for the Animal class
     * @param name
     * @param health
     * @param xCoord
     * @param yCoord
     * @param movementSpeed
     * @param weapon
     * @param rabid
     */
    public Animal(String name, int health, int xCoord, int yCoord, int movementSpeed, boolean rabid) {
        super(name, health, xCoord, yCoord, movementSpeed);
        this.rabid = rabid;
    }

    /**
     * Returns whether the animal is rabid
     * @return
     */
    public boolean isRabid() {
        return rabid;
    }

    /**
     * Sets whether the animal is rabid
     * @param rabid
     */
    public void setRabid(boolean rabid) {
        this.rabid = rabid;
    }
}

