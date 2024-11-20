package com.game.view;

import javax.swing.*;
import java.awt.*;
import com.game.model.Characters.subclasses.Player;
import com.game.model.Items.Item;

/**
* 
 * Lead Author(s):
 * @author Jesse Marino
 * 
 * Version/date: 11-15-2024
 * 
 * Responsibilities of class:
 * GUI for the player's inventory screen that can be displayed by pressing I
 * Extends JFrame
 */

public class InventoryScreen extends JFrame {
    // Instance variables
    private boolean visable = false;
    private Player player;
    private JPanel inventoryPanel;
    
    /**
     * Constructor for the InventoryScreen class
     * @param player
     */
    public InventoryScreen(Player player) {
        this.player = player;

        // Set the JFrame properties
        setTitle("Inventory");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        // Initialize the inventory panel
        inventoryPanel = new JPanel();
        inventoryPanel.setLayout(new GridLayout(5, 4, 10, 10)); // 5x4 grid with gaps
        add(inventoryPanel);
    }
    
    /**
     * Updates the inventory screen to reflect the current state of the player's inventory
     */
    public void updateInventory() {
        // Remove all existing buttons
        inventoryPanel.removeAll();

        // Add items from player's inventory
        for (Item item : player.getInventory()) {
            JButton itemButton = new JButton(item.getName());
            itemButton.setToolTipText("Right-click for options");
            inventoryPanel.add(itemButton);
        }
        
        // Fill remaining slots with empty panels
        int remainingSlots = 20 - player.getInventory().size();
        for (int i = 0; i < remainingSlots; i++) {
            JButton emptyButton = new JButton("Empty");
            inventoryPanel.add(emptyButton);
        }
        
        // Refresh the inventory panel
        revalidate();
        repaint();
    }

    /**
     * Returns the visibility of the inventory screen
     * @return
     */
    public boolean isVisible() {
        return visable;
    }
    
    /**
     * Shows the inventory screen
     */
    public void showInventory() {
        updateInventory();
        setVisible(true);
        visable = true;
    }
    
    /**
     * Hides the inventory screen
     */
    public void hideInventory() {
        setVisible(false);
        visable = false;
    }
}
