package com.game;

import com.game.Characters.subclasses.Animal;
import com.game.Characters.subclasses.Player;
import com.game.Items.Item;
import com.game.Merchants.Merchant;
import java.util.ArrayList;
import java.util.HashMap;
/**
* 
 * Lead Author(s):
 * @author Jesse Marino
 * 
 * Version/date: 10-30-2024
 * 
 * Responsibilities of class:
 * Handles the world state and game logic
 * 
 */
public class World {
    // Instance variables
    private Player player;
    private HashMap<String, Item> animals;
    private HashMap<String, Item> weapons;
    private HashMap<String, Item> clothing;
    private HashMap<String, Item> consumables;
    private HashMap<String, Item> tools;
    private HashMap<Integer, Item> merchants;
    
    // Constructor
    public World(String playerName) {

        populatePlayer(playerName);

    }

    /**
     * Populates the player
     */
    private void populatePlayer(String playerName) {
        // TODO: Create a starting inventory

        // TODO: Create a starting weapon
        // TODO: Create a starting clothing set
        player = new Player(playerName, 100, 300, 400, 10, null, null, null, null, 15);
    }
    
    

    /**
     * Starts the game
     */
    public void startGame() {
        // TODO: Implement game start logic
    }
    
    /**
     * Will be implemented later
     */
    public void loadGame() {
        // TODO: should load game state from a file
    }
    
    /**
     * Will be implemented later
     */
    public void saveGame() {
        // TODO: Should save game state to a file
    }
}

