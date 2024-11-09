package com.game.view;

import javax.swing.*;

import com.game.controller.GameController;
import com.game.model.World;

/**
* 
 * Lead Author(s):
 * @author Jesse Marino
 * 
 * Version/date: 10-25-2024
 * 
 * Responsibilities of class:
 * The main class for the game, extends JFrame and interacts with the start screen and game screen
 * 
 */

public class Game extends JFrame {
    // Instance variables
    private StartScreen startScreen;
    private GameScreen gameScreen;
    
    /**
     * Constructor for the Game class
     */
    public Game() {
        // Set the title of the window
        setTitle("| | FOREST || SPRINTER | |");
        // Set the default close operation
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Set the size of the window
        setSize(800, 600);
        // Set the location of the window
        setLocationRelativeTo(null);
        
        // Initialize the start screen
        startScreen = new StartScreen(this);

        // Add the start screen to the window
        add(startScreen);
    }
    
    /**
     * Starts the game with the player's name
     * @param playerName
     */
    public void startGame(String playerName) {
        // Create the world with the player's name
        World world = new World(playerName);

        // Create the game screen
        gameScreen = new GameScreen(world);

        // Create the game controller
        GameController controller = new GameController(world, gameScreen);
        
        // Replace the start screen with the game screen
        remove(startScreen);
        add(gameScreen);
        
        // Request focus after adding the gameScreen
        gameScreen.requestFocusInWindow();
        controller.startGame(); 
        
        // Refresh the window
        revalidate();
        repaint();
    }

    /**
     * Main method to run the game
     * @param args
     */
    public static void main(String[] args) {
        // Create the game
        Game game = new Game();
        // Make the game visible
        game.setVisible(true);
    }
}
