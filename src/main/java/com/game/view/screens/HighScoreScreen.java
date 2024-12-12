package com.game.view.screens;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.game.view.Game;

/**
* 
 * Lead Author(s):
 * @author Jesse Marino
 * 
 * Version/date: 12-11-2024 
 * 
 * Responsibilities of class:
 * The GUI for the high score screen where a player can see the high scores, extends JPanel
 * 
 */

public class HighScoreScreen extends JPanel {
    // Instance variables
    private Game game;
    private String playerName;

    /**
     * Constructor for the HighScoreScreen class
     * @param game
     * @param playerName
     */
    public HighScoreScreen(Game game, String playerName) {
        this.game = game;
        this.playerName = playerName;

        // Set JPanel settings
        setPreferredSize(new Dimension(400, 600));
        setDoubleBuffered(true);

        // Create main panel with vertical BoxLayout
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        
        // Add title
        JLabel titleLabel = new JLabel("High Scores");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        
        // Create scores panel
        JPanel scoresPanel = new JPanel();
        scoresPanel.setLayout(new BoxLayout(scoresPanel, BoxLayout.Y_AXIS));
        
        // Read and display scores
        try {
            BufferedReader reader = new BufferedReader(
                new FileReader("src/main/resources/highscores.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                JLabel scoreLabel = new JLabel(line);
                scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                scoresPanel.add(scoreLabel);
                scoresPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            }
            reader.close();
        } catch (IOException e) {
            JLabel errorLabel = new JLabel("Error loading high scores");
            errorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            scoresPanel.add(errorLabel);
        }
        
        // Add scroll pane for scores
        JScrollPane scrollPane = new JScrollPane(scoresPanel);
        scrollPane.setPreferredSize(new Dimension(350, 400));
        mainPanel.add(scrollPane);
        
        // Create button panel with horizontal layout
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Create play again button
        JButton playAgainButton = new JButton("Play Again");
        playAgainButton.addActionListener(e -> {
            game.startGame(this, playerName);
        });
        
        // Create exit button
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> {
            System.exit(0);
        });

        // Create start screen button
        JButton startScreenButton = new JButton("Start Screen");
        startScreenButton.addActionListener(e -> {
            game.remove(this);
            StartScreen startScreen = new StartScreen(game);
            game.add(startScreen);
            game.revalidate();
            game.repaint();
        });
        
        // Add buttons with spacing between them
        buttonPanel.add(playAgainButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        buttonPanel.add(exitButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        buttonPanel.add(startScreenButton);

        // Add buttons to the main panel
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(buttonPanel);
        
        // Add padding around the main panel
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Add the main panel to the high score screen
        add(mainPanel);
    }
} 
