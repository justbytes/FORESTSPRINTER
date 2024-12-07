package com.game.controller;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import com.game.model.Characters.subclasses.Animal;
import com.game.model.Characters.subclasses.Player;
import com.game.model.Items.Item;
import com.game.model.Items.subclasses.Clothing;
import com.game.model.Items.subclasses.Consumable;
import com.game.model.Items.subclasses.Tool;
import com.game.model.Items.subclasses.Weapon;
import com.game.model.Merchants.Merchant;
import com.game.model.World;
import com.game.view.modals.InventoryModal;
import com.game.view.modals.MerchantModal;
import com.game.view.screens.GameScreen;

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
    private InventoryModal inventoryScreen;
    private MerchantModal merchantScreen;
    

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

        // Set up WASD bindings along with attack, interact, and toggle inventory
        inputMap.put(KeyStroke.getKeyStroke("W"), "moveUp");
        inputMap.put(KeyStroke.getKeyStroke("S"), "moveDown");
        inputMap.put(KeyStroke.getKeyStroke("A"), "moveLeft");
        inputMap.put(KeyStroke.getKeyStroke("D"), "moveRight");
        inputMap.put(KeyStroke.getKeyStroke("I"), "toggleInventory");
        inputMap.put(KeyStroke.getKeyStroke("F"), "interact");
        inputMap.put(KeyStroke.getKeyStroke("J"), "attack");


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

        // Handle the I key press
        actionMap.put("toggleInventory", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Toggling inventory");
                handleKeyPress(KeyEvent.VK_I);
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

        // Handle the J key press
        actionMap.put("attack", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Attacking");
                attack();
            }
        });

        // Event listener for the mouse click which will be used to attack
        gameScreen.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    System.out.println("Mouse clicked");
                    attack();
                }
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
            inventoryScreen = new InventoryModal(world, world.getPlayer());
        }
        
        // Toggle the inventory screen visibility
        if (inventoryScreen.isVisible()) {
            inventoryScreen.hideInventory();
        } else {
            inventoryScreen.showInventory();
        }
    }

    /**
     * Interacts with an object in the player's vicinity
     */
    private void interact() {
        // Get the player and player coordinates from the world
        Player player = world.getPlayer();
        int[] playerCoords = player.getCoords();

        // Check for items or merchants the player can interact with and try to collect them
        interactWithItems(world.getWeapons(), playerCoords);
        interactWithItems(world.getTools(), playerCoords);
        interactWithItems(world.getConsumables(), playerCoords);
        interactWithItems(world.getClothing(), playerCoords);
        interactWithMerchant(world.getMerchants(), player);
    }

    /**
     * Checks and collects items if the player collides with them
     * @param items List of items to check
     * @param playerCoords Player's current coordinates
     */
    private <T extends Item> void interactWithItems(ArrayList<T> items, int[] playerCoords) {

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
                // If the player can collect the item and the inventory is not full
                // remove the item from the world
                if (world.getPlayer().collectItem(item)) {
                    if (item instanceof Weapon) {
                        ArrayList<Weapon> updatedWeapons = world.getWeapons();
                        updatedWeapons.remove((Weapon) item);
                        world.setWeapons(updatedWeapons);
                    } else if (item instanceof Tool) {
                        ArrayList<Tool> updatedTools = world.getTools();
                        updatedTools.remove((Tool) item);
                        world.setTools(updatedTools);
                    } else if (item instanceof Consumable) {
                        ArrayList<Consumable> updatedConsumables = world.getConsumables();
                        updatedConsumables.remove((Consumable) item);
                        world.setConsumables(updatedConsumables);
                    } else if (item instanceof Clothing) {
                        ArrayList<Clothing> updatedClothing = world.getClothing();
                        updatedClothing.remove((Clothing) item);
                        world.setClothing(updatedClothing);
                    }
                    System.out.println("Collected " + item.getName());
                } else {
                    JOptionPane.showMessageDialog(null, "Your inventory is full! Try selling or dropping some items");  
                }
            }
        }
    }


    /**
     * Interacts with a merchant to buy and sell items
     * Will create a merchant modal if the player interacts with a merchant
     */
    private void interactWithMerchant(ArrayList<Merchant> merchants, Player player) {
        // Get the player's coordinates
        int[] playerCoords = player.getCoords();

        // Iterate through the merchants
        Iterator<Merchant> iterator = merchants.iterator();

        // While there are merchants to be checked
        while (iterator.hasNext()) {
            // Get the next merchant
            Merchant merchant = iterator.next();

            // Get the merchant's coordinates
            int[] merchantCoords = merchant.getCoords();

            // Get player and merchant boundaries
            int playerLeft = playerCoords[0];
            int playerRight = playerCoords[0] + 25; 
            int playerTop = playerCoords[1];
            int playerBottom = playerCoords[1] + 25; 
            
            // Get merchant boundaries
            int merchantLeft = merchantCoords[0];
            int merchantRight = merchantCoords[0] + 50;  
            int merchantTop = merchantCoords[1];
            int merchantBottom = merchantCoords[1] + 100;

            // Check for collision 
            if (playerLeft < merchantRight && playerRight > merchantLeft &&
                playerTop < merchantBottom && playerBottom > merchantTop) {
                
                // If the merchant modal is already open, dispose of it
                if (merchantScreen != null) {
                    merchantScreen.dispose();
                }
                
                // Create a new merchant modal
                merchantScreen = new MerchantModal(merchant, player, world);
                merchantScreen.showMerchant();
                
            }
        }
    }

    /**
     * Attacks an animal with the player's weapon
     */
    private void attack() {
        // Get the player and player coordinates from the world
        Player player = world.getPlayer();
        int[] playerCoords = player.getCoords();
        Weapon weapon = player.getWeapon();

        // Get the animals in the world
        ArrayList<Animal> animals = world.getAnimals();

        // Iterate through the animals
        Iterator<Animal> iterator = animals.iterator();

        // While there are animals to be checked
        while (iterator.hasNext()) {
            // Get the next animal
            Animal animal = iterator.next();
            
            // Get the animal's coordinates
            int[] animalCoords = animal.getCoords();

            // Get player and animal boundaries
            int playerLeft = playerCoords[0];
            int playerRight = playerCoords[0] + 25; 
            int playerTop = playerCoords[1];
            int playerBottom = playerCoords[1] + 25; 
            
            // Get animal boundaries
            int animalLeft = animalCoords[0];
            int animalRight = animalCoords[0] + 25;  
            int animalTop = animalCoords[1];
            int animalBottom = animalCoords[1] + 25;
            
            // Check for collision 
            if (playerLeft < animalRight && playerRight > animalLeft &&
                playerTop < animalBottom && playerBottom > animalTop) {

                // Decrease the animal's health by the weapon's damage
                animal.decreaseHealth(weapon.getDamage());

                // If the player can kills the animal remove the animal from the world
                if (animal.getHealth() <= 0) {
                    ArrayList<Animal> updatedAnimals = world.getAnimals();
                    updatedAnimals.remove(animal);
                    world.setAnimals(updatedAnimals);
                    System.out.println("Animal " + animal.getName() + " has been killed!");
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
