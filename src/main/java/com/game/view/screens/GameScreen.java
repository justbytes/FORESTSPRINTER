package com.game.view.screens;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import com.game.model.Characters.subclasses.Animal;
import com.game.model.Characters.subclasses.Player;
import com.game.model.Items.subclasses.Clothing;
import com.game.model.Items.subclasses.Consumable;
import com.game.model.Items.subclasses.Tool;
import com.game.model.Items.subclasses.Weapon;
import com.game.model.Merchants.Merchant;
import com.game.model.World;

/**
* 
 * Lead Author(s):
 * @author Jesse Marino
 * 
 * Version/date: 11-06-2024 - 11-08-2024
 * 
 * Responsibilities of class:
 * Handles the game screen and rendering of the game objects
 * 
 */

public class GameScreen extends JPanel {
    // Instance variables
    private static final int WORLD_WIDTH = 1800;
    private static final int WORLD_HEIGHT = 1200;
    private static final int VIEWPORT_WIDTH = 800;
    private static final int VIEWPORT_HEIGHT = 600;
    private World world;
    private int cameraX = 0;
    private int cameraY = 0;

    /**
     * Constructor for the GameScreen class
     * @param world
     */
    public GameScreen(World world) {
        this.world = world;
        setPreferredSize(new Dimension(VIEWPORT_WIDTH, VIEWPORT_HEIGHT));
        setDoubleBuffered(true);
    }

    /**
     * Paints the game screen
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Use Graphics2D for better graphics
        Graphics2D g2d = (Graphics2D) g;

        // Draw water background for entire world or the water border
        g2d.setColor(Color.BLUE);
        g2d.fillRect(-cameraX, -cameraY, WORLD_WIDTH, WORLD_HEIGHT);

        // Draw green ground or the playable area
        g2d.setColor(Color.GREEN);
        int groundBorder = 50; // Size of water border in pixels
        g2d.fillRect(
            groundBorder - cameraX,
            groundBorder - cameraY,
            WORLD_WIDTH - (2 * groundBorder),
            WORLD_HEIGHT - (2 * groundBorder)
        );

        // Draw all tools in world
        g2d.setColor(Color.GRAY);
        for (Tool tool : world.getTools()) {
            int[] coords = tool.getCoords();
            g2d.fillRect(
                coords[0] - cameraX,
                coords[1] - cameraY,
                15,
                15
            );
        }

        // Create a size instance variable for each Item then set it to the appropriate size so it can be used to draw and check for collisions
        // Draw all weapons in world
        g2d.setColor(Color.MAGENTA);
        for (Weapon weapon : world.getWeapons()) {
            int[] coords = weapon.getCoords();
            g2d.fillRect(
                coords[0] - cameraX,
                coords[1] - cameraY,
                15,
                15
            );
        }

        // Draw all merchants in world
        g2d.setColor(Color.ORANGE);
        for (Merchant merchant : world.getMerchants()) {
            int[] coords = merchant.getCoords();
            g2d.fillRect(
                coords[0] - cameraX,
                coords[1] - cameraY,
                50,
                100
            );
        }

        // Draw all consumables in world
        g2d.setColor(Color.BLUE);
        for (Consumable consumable : world.getConsumables()) {
            int[] coords = consumable.getCoords();
            g2d.fillOval(  // Using oval for variety
                coords[0] - cameraX,
                coords[1] - cameraY,
                15,  // width
                15   // height
            );
        }

        // Draw all clothing items in world
        g2d.setColor(Color.YELLOW);
        for (Clothing clothing : world.getClothing()) {
            int[] coords = clothing.getCoords();
            g2d.fillOval(
                coords[0] - cameraX,
                coords[1] - cameraY,
                15,  // width
                15   // height
            );
        }

        // Draw all animals in world
        g2d.setColor(Color.BLACK);
        for (Animal animal : world.getAnimals()) {
            int[] coords = animal.getCoords();
                g2d.fillOval(
                coords[0] - cameraX,
                coords[1] - cameraY,
                25,  // width
                25   // height
            );
        }

        // Draw player last so it appears on top
        g2d.setColor(Color.RED);
        int[] playerCoords = world.getPlayer().getCoords();
        g2d.fillRect(
            playerCoords[0] - cameraX,
            playerCoords[1] - cameraY,
            25,  // width
            25   // height
        );

        // Set up text rendering
        g2d.setRenderingHint(
            RenderingHints.KEY_TEXT_ANTIALIASING,
            RenderingHints.VALUE_TEXT_ANTIALIAS_ON
        );
        
        // Set font style
        Font gameFont = new Font("Arial", Font.BOLD, 14);
        g2d.setFont(gameFont);
        
        // Position for stats at bottom of screen
        int y = VIEWPORT_HEIGHT - 30; // 30 pixels from bottom
        g2d.setColor(Color.WHITE);

        // Get the player 
        Player player = world.getPlayer();
        
        // Calculate spacing between stats
        int spacing = VIEWPORT_WIDTH / 5;
        
        // Draw stats horizontally
        g2d.drawString("Player: " + player.getName(), 10, y);
        g2d.drawString("Health: " + player.getHealth() + "/100", spacing, y);
        g2d.drawString("Kills: " + player.getKills(), spacing * 2, y);
        g2d.drawString("Coins: " + player.getCoins(), spacing * 3, y);
        g2d.drawString("Weapon: " + player.getWeapon().getName(), spacing * 4, y);
    }

    /**
     * Updates the camera position based on the player's position
     * @param playerX
     * @param playerY
     */
    public void updateCamera(int playerX, int playerY) {
        // Center the camera position on the player
        cameraX = playerX - (VIEWPORT_WIDTH / 2);
        cameraY = playerY - (VIEWPORT_HEIGHT / 2);
        
        // Prevent showing area beyond world boundaries
        cameraX = Math.max(0, Math.min(cameraX, WORLD_WIDTH - VIEWPORT_WIDTH));
        cameraY = Math.max(0, Math.min(cameraY, WORLD_HEIGHT - VIEWPORT_HEIGHT));
        
        // Repaint the game screen
        repaint();
    }

    /**
     * Getter for the world width
     * @return
     */
    public int getWorldWidth() {
        return WORLD_WIDTH;
    }

    /**
     * Getter for the world height
     * @return
     */ 
    public int getWorldHeight() {
        return WORLD_HEIGHT;
    }
}
