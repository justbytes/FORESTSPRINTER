package com.game.model.Merchants;
import com.game.model.Characters.subclasses.Player;
import com.game.model.Items.Item;
import com.game.model.Items.subclasses.Clothing;
import com.game.model.Items.subclasses.Weapon;

import java.util.ArrayList;

/**
* 
 * Lead Author(s):
 * @author Jesse Marino
 * 
 * Version/date: 10-25-2024
 * 
 * Responsibilities of class:
 * Handles the merchant's inventory and transactions with the player
 * 
 */
public class Merchant {
    // Instance variables
    private String name;
    private Class<? extends Item> favoriteItemType; // Consumable, Clothing, Tool, Weapon etc...
    private int xCoord;
    private int yCoord;
    private ArrayList<Item> inventory;
    private int coins;

    /**
     * Constructor for the Merchant class
     * @param name
     * @param x
     * @param y
     * @param inventory
     * @param favoriteItemType
     */
    public Merchant(String name, int x, int y, ArrayList<? extends Item> inventory, Class<? extends Item> favoriteItemType) {
        this.name = name;
        this.xCoord = x;
        this.yCoord = y;
        this.inventory = new ArrayList<>(inventory);
        this.favoriteItemType = favoriteItemType;
        this.coins = 1000; // Starting coins
    }

    /**
     * Get the merchant's favorite item type
     * @return
     */
    public Class<? extends Item> getFavoriteItemType() {
        return favoriteItemType;
    }

    /**
     * Get the merchant's name
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Get the merchant's coordinates
     * @return
     */
    public int[] getCoords() {
        return new int[]{xCoord, yCoord};
    }

    /**
     * Get a copy of the merchant's inventory
     * @return
     */
    public ArrayList<Item> getInventory() {
        // Return a copy of the inventory
        return new ArrayList<Item>(inventory);
    }

    /**
     * Set the merchant's inventory
     * @param inventory
     */
    public void setInventory(ArrayList<Item> inventory) {
        this.inventory = new ArrayList<Item>(inventory);
    }

    /**
     * Remove an item from the merchant's inventory
     * @param item
     */
    public void removeItem(Item item) {
        // Loop through until the item is found and remove it
        for (Item i : inventory) {
            if (i.getName().equals(item.getName())) {
                inventory.remove(i);
                break;
            }
        }
    }

    /**
     * Buy an item from the player | These are backwards and thats how theyre going to stay because its working
     * @param player
     * @param item
     * @return
     */
    public boolean sellItem(Player player, Item item) {
        int price = item.getPrice();
        if (coins >= price) {
            // If the merchant's favorite item type is the same as the item's type, increase the price by 30%
            if (item.getClass().equals(favoriteItemType)) {
                price = (int) (price * 1.15); // 15% price increase
            }

            // Remove the coins from the merchant
            coins -= price;
            // Add the coins to the player
            player.addCoins(price);

            // If the item is a weapon ensure it is unequipped before being sold/ remove it from the player so they cant use it
            if (item instanceof Weapon ) {
                // If the player sells an equipped weapon, unequip it
                if(player.getWeapon().equals(item)) {
                    // Unequip the weapon
                    item.equip();
                    // Equip a default weapon
                    Weapon defaultWeapon = new Weapon("Fist", "A fist for punching", 0, 0, 0, "Common", 1);
                    player.equipWeapon(defaultWeapon);
                } else if (item instanceof Clothing) {
                    // If the player sells an equipped clothing, unequip it
                    if(player.getClothingTop().equals(item)) {
                        item.equip();
                        Clothing defaultClothing = new Clothing("None", "Bare", 0, 0, 0, "Common", 0, "Top");
                        player.setClothingTop(defaultClothing);
                    }
                } else if (player.getClothingBottom().equals(item)) {
                    item.equip();
                    Clothing defaultClothing = new Clothing("None", "Bare", 0, 0, 0, "Common", 0, "Bottom");
                    player.setClothingBottom(defaultClothing);
                }
            }

            // Remove the item from the player's inventory
            player.removeItem(item);
            // Add the item to the merchant's inventory
            inventory.add(item);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Sell an item to the player | These are backwards and thats how theyre going to stay because its working
     * @param player
     * @param item
     */
    public boolean buyItem(Player player, Item item) {
        // Return false if the player's inventory is full
        if (player.getInventory().size() >= 20) {
            return false;
        }
        // Get the price of the item
        int price = item.getPrice();

        // If the player has enough coins, remove the coins and add the item to the player's inventory
        if (player.getCoins() >= price) {
            // Remove the coins from the player
            player.removeCoins(price);
            // Add the coins to the merchant
            coins += price;
            // Add the item to the player's inventory
            player.collectItem(item);
            // Remove the item from the merchant's inventory
            removeItem(item);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Get the merchant's coins
     * @return
     */
    public int getCoins() {
        return coins;
    }
}
