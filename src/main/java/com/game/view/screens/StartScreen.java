package com.game.view.screens;

import javax.swing.*;

import com.game.view.Game;

import java.awt.*;

/**
* 
 * Lead Author(s):
 * @author Jesse Marino
 * 
 * Version/date: 11-05-2024 - 11-07-2024
 * 
 * Responsibilities of class:
 * The GUI for the start screen where a player can enter their name and start the game, extends JPanel
 * 
 */

public class StartScreen extends JPanel {
    private JTextField nameField;
    private JButton startButton;
    private Game game;

    /**
     * Constructor for the StartScreen class
     * @param game
     */
    public StartScreen(Game game) {
        this.game = game;
        setLayout(new GridBagLayout());
        initializeComponents();
    }

    /**
     * Initializes the components for the start screen
     */
    private void initializeComponents() {
        // set a grid bag layout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Title Label
        JLabel titleLabel = new JLabel("Welcome to Forest Sprinter!");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(titleLabel, gbc);

        // Name Label
        JLabel nameLabel = new JLabel("Enter your name:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(nameLabel, gbc);

        // Name Text Field
        nameField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(nameField, gbc);

        // Start Button
        startButton = new JButton("Start Game");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        startButton.addActionListener(e -> startGame());
        add(startButton, gbc);
    }

    /**
     * Starts the game with the player's name
     */
    private void startGame() {
        String playerName = nameField.getText().trim();

        // Check if the player's name is empty
        if (playerName.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Please enter your name!", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Start the game with the player's name
        game.startGame(playerName);
    }
}
