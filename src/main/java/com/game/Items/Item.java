package com.game.Items;
/**
* 
 * Lead Author(s):
 * @author Jesse Marino
 * 
 * Version/date: 10-25-2024
 * 
 * Responsibilities of class:
 * Represents an item
 * 
 */
public class Item {
    // Instance Variables
    private String status;
    private String description;
    private int xCoords;
    private int yCoords;
    private int price;
    private String name;
    

    /**
     * Constructor for the Item class
     * @param name
     * @param description
     * @param price
     * @param x
     * @param y
     * @param status
     */
    public Item(String name, String description, int price, int x, int y, String status) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.xCoords = x;
        this.yCoords = y;
        this.status = status;
    }

    /**
     * Get the name of the item
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Get the description of the item
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * Get the price of the item
     * @return
     */
    public int getPrice() {
        return price;
    }

    /**
     * Get the coordinates of the item
     * @return
     */
    public int[] getCoords() {
        return new int[]{xCoords, yCoords};
    }

    /**
     * Get the status of the item
     * @return
     */
    public String getStatus() {
        return status;
    }
}
