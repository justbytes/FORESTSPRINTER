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
    private int kills = 0;

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
    public Player(String name, int health, int xCoord, int yCoord, int movementSpeed, ArrayList<Item> inventory, int coins) {
        super(name, health, xCoord, yCoord, movementSpeed);
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
    public void setClothingBottom(Clothing newClothingBottom) {    
        // If the player is not wearing any clothing, equip the new clothing
        if (this.clothingBottom == null) {
            this.clothingBottom = newClothingBottom;
            this.clothingBottom.equip();
        } else {
            // If the player is wearing clothing, unequip it
            if (this.clothingBottom.isEquipped()) {
                this.clothingBottom.equip();
            }

            // Equip the new clothing
            this.clothingBottom = newClothingBottom;
            this.clothingBottom.equip();
        }
    }

    /**
     * Set the top clothing of the player
     * @param clothingTop
     */
    public void setClothingTop(Clothing newClothingTop) {
        // If the player is not wearing any clothing, equip the new clothing
        if (this.clothingTop == null) {
            this.clothingTop = newClothingTop;
            this.clothingTop.equip();
        } else {
            // If the player is wearing clothing, unequip it
            if (this.clothingTop.isEquipped()) {
                this.clothingTop.equip();
            }

            // Equip the new clothing
            this.clothingTop = newClothingTop;
            this.clothingTop.equip();
        }
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
    public void equipWeapon(Weapon weapon) {
            super.setWeapon(weapon);
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

    /**
     * Get the kills of the player
     * @return
     */
    public int getKills() {
        return kills;
    }

    /**
     * Increases the kills of the player
     */
    public void increaseKills() {
        kills++;
    }
}

