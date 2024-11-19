package com.game.model.Characters.subclasses;
import java.util.ArrayList;

import com.game.model.Characters.Character;
import com.game.model.Items.Item;
import com.game.model.Items.subclasses.Clothing;
import com.game.model.Items.subclasses.Consumable;
import com.game.model.Items.subclasses.Weapon;

/**
* 
 * Lead Author(s):
 * @author Jesse Marino
 * 
 * Version/date: 10-25-2024
 * 
 * Responsibilities of class:
 * Represents a player that will controlled by a user and extends Character
 * 
 */
public class Player extends Character {
    private ArrayList<Item> inventory;
    private Clothing clothingBottom;
    private Clothing clothingTop;
    private int coins;

    /**
     * Constructor for the Player class
     * @param name
     * @param health
     * @param xCoord
     * @param yCoord
     * @param movementSpeed
     * @param weapon
     * @param clothingBottom
     * @param clothingTop
     * @param inventory
     * @param coins
     */
    public Player(String name, int health, int xCoord, int yCoord, int movementSpeed, Weapon weapon, Clothing clothingBottom, Clothing clothingTop, ArrayList<Item> inventory, int coins) {
        super(name, health, xCoord, yCoord, movementSpeed, weapon);
        this.clothingBottom = clothingBottom;
        this.clothingTop = clothingTop;
        this.inventory = inventory;
        this.coins = coins;
    }

    /**
     * Decreases the health of the player taking into account the protection of the clothing
     * @param amount
     * @return
     */
    @Override
    public int decreaseHealth(int amount) {
        int total;

        // Calculate the total protection from the clothing
        int totalProtection = totalProtection();
        total = amount - totalProtection;
        
        return super.decreaseHealth(total);
    }

    /**
     * Get the bottom clothing of the player
     * @return
     */
    public Clothing getClothingBottom() {
        return clothingBottom;
    }

    /**
     * Get the top clothing of the player
     * @return
     */
    public Clothing getClothingTop() {
        return clothingTop;
    }

    /**
     * Set the bottom clothing of the player
     * @param clothingBottom
     */
    public void setClothingBottom(Clothing clothingBottom) {
        this.clothingBottom = clothingBottom;
    }

    /**
     * Set the top clothing of the player
     * @param clothingTop
     */
    public void setClothingTop(Clothing clothingTop) {
        this.clothingTop = clothingTop;
    }

    /**
     * Get the total protection from the clothing
     * @return
     */
    public int totalProtection() {
        return clothingTop.getProtection() + clothingBottom.getProtection();
    }

    /**
     * Get the inventory of the player
     * @return
     */
    public ArrayList<Item> getInventory() {

        // Return a copy of the inventory
        ArrayList<Item> copy = new ArrayList<Item>(inventory);
        return copy;
    }

    /**
     * Collects an item if the inventory has less than 20 items
     * @param item
     * @return
     */
    public boolean collectItem(Item item) {
        System.out.println("Inventory size: " + inventory.size());
        System.out.println("Item: " + item.getName());
        
        // Check if the inventory has less than 20 items before adding
        if (inventory.size() < 20) {
            inventory.add(item);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Removes an item from the inventory
     * @param item
     */
    public void removeItem(Item item) {

        // Loop through the inventory and remove the item
        for (Item i : inventory) {

            // If the item is found, remove it
            if (i.getName().equals(item.getName())) {
                inventory.remove(i);
                break;
            }
        }
    }

    /**
     * Equips a weapon to the player
     * @param weapon
     * @return
     */
    public boolean equipWeapon(Weapon weapon) {

        // Get the current weapon
        Weapon currentWeapon = super.getWeapon();

        // Check if the player can store the current weapon in their inventory
        boolean addToInventory = collectItem(currentWeapon);

        // If the player has space, equip the weapon and return true
        if (addToInventory) {
            super.setWeapon(weapon);
            return true;    
        } else {
            return false;
        }
    }

    /**
     * Removes coins from the player
     * @param amount
     */
    public void removeCoins(int amount) {

        // Check if the player has enough coins
        if (coins - amount < 0) {
            coins = 0;
        } else {
            coins -= amount;
        }
    }

    /**
     * Adds coins to the player
     * @param amount
     */
    public void addCoins(int amount) {
        coins += amount;
    }

    /**
     * Get the coins of the player
     * @return
     */
    public int getCoins() {
        return coins;
    }

    /**
     * Uses a consumable to heal the player
     * @param consumable
     */
    public void useConsumable(Consumable consumable) {
        // Get the healing properties of the consumable
        int healingProps = consumable.getHealingProps();

        // Increase the health of the player
        increaseHealth(healingProps);

        // Remove the consumable from the inventory
        removeItem(consumable);
    }
}

