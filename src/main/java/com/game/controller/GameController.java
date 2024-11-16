package com.game.controller;

import com.game.model.World;
import com.game.model.Characters.subclasses.Player;
import com.game.model.Items.Item;
import com.game.view.GameScreen;

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
 * Version/date: 11-06-2024 - 11-08-2024
 * 
 * Responsibilities of class:
 * Handles/controls the game objects and logic
 * 
 */

public class GameController {
    // Instance variables
    private World world;
    private GameScreen gameScreen;

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
        inputMap.put(KeyStroke.getKeyStroke("TAB"), "toggleInventory");
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
            case KeyEvent.VK_TAB:
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
     * Toggles the inventory
     */
    private void toggleInventory() {
        // TODO: Implement inventory toggle
    }

    /**
     * Interacts with an object in the player's vicinity
     */
    private void interact() {
        Player player = world.getPlayer();
        int[] playerCoords = player.getCoords();
        

        // Check for items in range and try to collect them
        checkAndCollectItems(world.getWeapons(), playerCoords);
        checkAndCollectItems(world.getTools(), playerCoords);
        checkAndCollectItems(world.getConsumables(), playerCoords);
        checkAndCollectItems(world.getClothing(), playerCoords);
    }

    /**
     * Helper method to check and collect items within range
     * @param items List of items to check
     * @param playerCoords Player's current coordinates
     */
    private <T extends Item> void checkAndCollectItems(ArrayList<T> items, int[] playerCoords) {
        Iterator<T> iterator = items.iterator();
        while (iterator.hasNext()) {
            T item = iterator.next();
            int[] itemCoords = item.getCoords();
            
            // Calculate distance between player and item
            double distance = Math.sqrt(
                Math.pow(playerCoords[0] - itemCoords[0], 2) +
                Math.pow(playerCoords[1] - itemCoords[1], 2)
            );

            // If item is within range, try to collect it
            
            if (world.getPlayer().collectItem(item)) {
                iterator.remove(); // Remove item from world if successfully collected
                System.out.println("Collected " + item.getName());
            } else {
                System.out.println("Inventory is full!");
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
