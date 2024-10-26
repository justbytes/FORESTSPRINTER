package com.game.Items;
/**
* 
 * Lead Author(s):
 * @author Jesse Marino
 * 
 * Version/date: 10-25-2024
 * 
 * Responsibilities of class:
 * Represents a tool extends item
 * 
 */
public class Tool extends Item {
    private String utility;

    /**
     * Constructor for the Tool class
     * @param name
     * @param description
     * @param price
     * @param x
     * @param y
     * @param status
     * @param utility
     */
    public Tool(String name, String description, int price, int x, int y, String status, String utility) {
        super(name, description, price, x, y, status);
        this.utility = utility; // "mining", "woodcutting", "fishing", "crafting"
    }

    /**
     * Get the utility of the tool
     * @return
     */
    public String getUtility() {
        return utility;
    }
}
