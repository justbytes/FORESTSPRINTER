package com.game.Items.subclasses;

import com.game.Items.Item;

/**
* 
 * Lead Author(s):
 * @author Jesse Marino
 * 
 * Version/date: 10-25-2024
 * 
 * Responsibilities of class:
 * Represents a consumable extends item
 * 
 */
public class Consumable extends Item {
    private int healingProps;

    /**
     * Constructor for the Consumable class
     * @param name
     * @param description
     * @param price
     * @param x
     * @param y
     * @param status
     * @param healingProps
     */
    public Consumable(String name, String description, int price, int x, int y, String status, int healingProps) {
        super(name, description, price, x, y, status);
        this.healingProps = healingProps;
    }

    /**
     * Get the healing properties of the consumable
     * @return
     */
    public int getHealingProps() {
        return healingProps;
    }
}
