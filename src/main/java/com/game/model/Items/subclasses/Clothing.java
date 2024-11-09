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
 * Represents a clothing extends item
 * 
 */
public class Clothing extends Item {
    private int protection;
    private String type;

    /**
     * Constructor for the Clothing class
     * @param name
     * @param description
     * @param price
     * @param x
     * @param y
     * @param status
     * @param protection
     * @param type
     */
    public Clothing(String name, String description, int price, int x, int y, String status, int protection, String type) {
        super(name, description, price, x, y, status);
        this.protection = protection;
        this.type = type; // "bottom" or "top"
    }

    /**
     * Get the protection of the clothing
     * @return
     */
    public int getProtection() {
        return protection;
    }

    /**
     * Get the type of the clothing (bottom or top)
     * @return
     */
    public String getType() {
        return type;
    }
}
