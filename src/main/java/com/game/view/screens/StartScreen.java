package com.game.view.screens;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.game.view.Game;

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
    // Instance variables
    private JTextField nameField;
    private JButton startButton;
    private Game game;

    /**
     * Constructor for the StartScreen class
     * @param game
     */
    public StartScreen(Game game) {
        this.game = game;

        // Set JPanel layout
        setLayout(new GridBagLayout());

        // Initialize components
        initializeComponents();
    }

    /**
     * Initializes the components for the start screen
     */
    private void initializeComponents() {
        // Set GridBagConstraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Title Label
        JLabel titleLabel = new JLabel("Welcome to Forest Sprinter!");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(titleLabel, gbc);

        // Instructions - each line as a separate label
        String[] instructions = {
            "• Use A, W, D, and S to move the red square (your character)",
            "• Press I to open the inventory - equip weapons, armor, or use items",
            "• Press F to interact with items on the map:",
            "     - Yellow boxes are Merchants (opens trading menu)",
            "     - Other items can be picked up",
            "• Left click to attack and defend yourself against animals"
        };

        // Set font for instructions
        Font instructionFont = new Font("Arial", Font.PLAIN, 14);
        for (int i = 0; i < instructions.length; i++) {
            JLabel instructionLine = new JLabel(instructions[i]);
            instructionLine.setFont(instructionFont);
            gbc.gridx = 0;
            gbc.gridy = i + 1;
            gbc.gridwidth = 2;
            add(instructionLine, gbc);
        }

        // Name Label
        JLabel nameLabel = new JLabel("Enter your name:");
        gbc.gridx = 0;
        gbc.gridy = instructions.length + 1;
        gbc.gridwidth = 1;
        add(nameLabel, gbc);

        // Name Text Field
        nameField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = instructions.length + 1;
        add(nameField, gbc);

        // Start Button
        startButton = new JButton("Start Game");
        gbc.gridx = 0;
        gbc.gridy = instructions.length + 2;
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
        game.startGame(this, playerName);
    }
}
