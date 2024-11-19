package com.game.controller;

import com.game.model.World;
import com.game.model.Characters.subclasses.Player;
import com.game.model.Items.Item;
import com.game.view.GameScreen;
import com.game.view.InventoryScreen;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

import java.util.Iterator;

/**
* 
 * Lead Author(s):
 * @author Jesse Marino
 * 
 * Version/date: 11-06-2024 - 11-08-2024 11-15-2024
 * 
 * Responsibilities of class:
 * Handles/controls the game objects and logic
 * 
 */

public class GameController {
    // Instance variables
    private World world;
    private GameScreen gameScreen;
    private InventoryScreen inventoryScreen;
    private boolean isInventoryOpen = false;

    /**
     * Constructor for the GameController class
     * @param world
     * @param gameScreen
     */
    public GameController(World world, GameScreen gameScreen) {
        this.world = world;
        this.gameScreen = gameScreen;
        initializeKeyBindings();
    }

    /**
     * Initializes the key bindings for the game
     */
    private void initializeKeyBindings() {
        // Get the input map and action map from the game screen
        InputMap inputMap = gameScreen.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = gameScreen.getActionMap();

        // Set up WASD bindings
        inputMap.put(KeyStroke.getKeyStroke("W"), "moveUp");
        inputMap.put(KeyStroke.getKeyStroke("S"), "moveDown");
        inputMap.put(KeyStroke.getKeyStroke("A"), "moveLeft");
        inputMap.put(KeyStroke.getKeyStroke("D"), "moveRight");
        inputMap.put(KeyStroke.getKeyStroke("I"), "toggleInventory");
        inputMap.put(KeyStroke.getKeyStroke("F"), "interact");

        // Event listener for the W key
        actionMap.put("moveUp", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Moving up");
                handleKeyPress(KeyEvent.VK_W);
            }
        });

        // Handle the S key press
        actionMap.put("moveDown", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Moving down");
                handleKeyPress(KeyEvent.VK_S);
            }
        });

        // Handle the A key press
        actionMap.put("moveLeft", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Moving left");
                handleKeyPress(KeyEvent.VK_A);
            }
        });

        // Handle the D key press
        actionMap.put("moveRight", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Moving right");
                handleKeyPress(KeyEvent.VK_D);
            }
        });

        // Handle the TAB key press
        actionMap.put("toggleInventory", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Toggling inventory");
                toggleInventory();
            }
        });

        // Handle the F key press
        actionMap.put("interact", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Interacting");
                interact();
            }
        });
    }

    /**
     * Moves the player based on the key pressed and current position
     * @param keyCode
     */
    private void handleKeyPress(int keyCode) {
        // Get the player and player data from the world
        Player player = world.getPlayer();
        int[] coords = player.getCoords();
        int x = coords[0];
        int y = coords[1];
        int moveSpeed = player.getMovementSpeed();

        switch (keyCode) {
            // Move up
            case KeyEvent.VK_W:
                if (isValidPosition(x, y - moveSpeed)) y -= moveSpeed;
                updatePlayerPosition(x, y);
                break;
            // Move down
            case KeyEvent.VK_S:
                if (isValidPosition(x, y + moveSpeed)) y += moveSpeed;
                updatePlayerPosition(x, y);
                break;
            // Move left
            case KeyEvent.VK_A:
                if (isValidPosition(x - moveSpeed, y)) x -= moveSpeed;
                updatePlayerPosition(x, y);
                break;
            // Move right
            case KeyEvent.VK_D:
                if (isValidPosition(x + moveSpeed, y)) x += moveSpeed;
                updatePlayerPosition(x, y);
                break;
            // Toggle inventory
            case KeyEvent.VK_I:
                System.out.println("Toggling inventory");
                toggleInventory();
                break;
        }  
    }

    /**
     * TODO: Should stop the player from moving through out of bounds areas, maybe can be used to stop a player from going through a wall 
     * @param x
     * @param y
     * @return
     */
    private boolean isValidPosition(int x, int y) {
        // Still needs work
        return x >= 50 && x <= gameScreen.getWorldWidth() - 25 &&
               y >= 50 && y <= gameScreen.getWorldHeight() - 25;
    }

    /**
     * Updates the player's position and camera
     * @param x
     * @param y
     */
    private void updatePlayerPosition(int x, int y) {
        world.getPlayer().setCoords(x, y);
        gameScreen.updateCamera(x, y);
    }

    /**
     * Toggles the inventory screen visibility
     */
    private void toggleInventory() {
        if (inventoryScreen == null) {
            // Initialize inventory screen if it hasn't been created yet
            inventoryScreen = new InventoryScreen(world.getPlayer());
        }
        
        // Toggle the inventory screen visibility
        if (isInventoryOpen) {
            inventoryScreen.hideInventory();
        } else {
            inventoryScreen.showInventory();
        }
        // set the inventory screen visibility
        isInventoryOpen = !isInventoryOpen;
    }

    /**
     * Interacts with an object in the player's vicinity
     */
    private void interact() {
        // Get the player and player coordinates from the world
        Player player = world.getPlayer();
        int[] playerCoords = player.getCoords();

        // Check for items the player can interact with and try to collect them
        checkAndCollectItems(world.getWeapons(), playerCoords);
        checkAndCollectItems(world.getTools(), playerCoords);
        checkAndCollectItems(world.getConsumables(), playerCoords);
        checkAndCollectItems(world.getClothing(), playerCoords);
    }

    /**
     * Checks and collects items if the player collides with them
     * @param items List of items to check
     * @param playerCoords Player's current coordinates
     */
    private <T extends Item> void checkAndCollectItems(ArrayList<T> items, int[] playerCoords) {

        // Reference: https://www.w3schools.com/java/java_iterator.asp
        // Using an iterator to iterate through the items list and remove the item if it is collected
        Iterator<T> iterator = items.iterator();

        // While there are items to be checked
        while (iterator.hasNext()) {
            // Get the next item
            T item = iterator.next();
            
            // Get the item's coordinates
            int[] itemCoords = item.getCoords();

            // Get player and item boundaries
            int playerLeft = playerCoords[0];
            int playerRight = playerCoords[0] + 25; 
            int playerTop = playerCoords[1];
            int playerBottom = playerCoords[1] + 25; 
            
            // Get item boundaries
            int itemLeft = itemCoords[0];
            int itemRight = itemCoords[0] + 15;  
            int itemTop = itemCoords[1];
            int itemBottom = itemCoords[1] + 15;  

            // Check for collision 
            if (playerLeft < itemRight && playerRight > itemLeft &&
                playerTop < itemBottom && playerBottom > itemTop) {
                // If the player can collect the item
                if (world.getPlayer().collectItem(item)) {
                    // Remove item from the list
                    iterator.remove();
                    System.out.println("Collected " + item.getName());
                } else {
                    System.out.println("Inventory is full!");
                }
            }
        }
    }

    /**
     * Focuses the game screen
     */
    public void startGame() {
        gameScreen.requestFocusInWindow();
    }
}
