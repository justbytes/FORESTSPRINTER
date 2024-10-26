package com.game.Characters;
import com.game.Items.Weapon;

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
    public Animal(String name, int health, int xCoord, int yCoord, int movementSpeed, Weapon weapon, boolean rabid) {
        super(name, health, xCoord, yCoord, movementSpeed, weapon);
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

