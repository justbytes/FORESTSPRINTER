package com.game.model.Merchants;
import com.game.model.Characters.subclasses.Player;
import com.game.model.Items.Item;

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

        for (Item i : inventory) {
            if (i.getName().equals(item.getName())) {
                inventory.remove(i);
                break;
            }
        }
    }

    /**
     * Buy an item from the player
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
            coins -= price;
            player.addCoins(price);
            player.removeItem(item);
            inventory.add(item);

            return true;
        } else {
            return false;
        }
    }

    /**
     * Sell an item to the player
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
            player.removeCoins(price);
            coins += price;
            player.collectItem(item);
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
