package com.game.model.Items.subclasses;

import com.game.model.Items.Item;

/**
* 
 * Lead Author(s):
 * @author Jesse Marino
 * 
 * Version/date: 10-25-2024
 * 
 * Responsibilities of class:
 * Represents a weapon extends item
 * 
 */
public class Weapon extends Item {
    // Instance variables
    private int damage;

    /**
     * Constructor for the Weapon class
     * @param name
     * @param description
     * @param price
     * @param x
     * @param y
     * @param status
     * @param damage
     */
    public Weapon(String name, String description, int price, int x, int y, String status, int damage) {
        super(name, description, price, x, y, status);
        this.damage = damage;
    }

    /**
     * Get the damage of the weapon
     * @return
     */
    public int getDamage() {
        return damage;
    }
}
