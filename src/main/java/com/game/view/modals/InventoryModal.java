package com.game.view.modals;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.game.model.Characters.subclasses.Player;
import com.game.model.Items.Item;
import com.game.model.World;

/**
 * Lead Author(s):
 * @author Jesse Marino
 * 
 * Version/date: 11-20-2024 11-22-2024
 * 
 * Responsibilities of class:
 * Creates the GUI modal for the player's inventory screen where they will be able to view and manage their inventory
 */
public class InventoryModal extends JFrame {
    // Instance variables
    private World world;
    private boolean visable = false;
    private Player player;
    private JPanel inventoryPanel;
    
    /**
     * Constructor for the InventoryModal class
     * @param player
     */
    public InventoryModal(World world, Player player) {
        this.world = world;
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
        setVisible(true);
    }
    
    /**
     * Updates the inventory screen to reflect the current state of the player's inventory
     */
    public void updateInventory() {
        // Remove all existing buttons
        inventoryPanel.removeAll();

        // Add items from player's inventory
        for (Item item : player.getInventory()) {
            // Creates a button for each item in the inventory
            JButton itemButton = new JButton(item.getName());

            // Adds an action listener to the button
            itemButton.addActionListener(e -> {
                // Creates an item modal
                ItemModal itemModal = new ItemModal(world, this, player, item);
            });

            // Adds the button to the inventory panel
            inventoryPanel.add(itemButton);
        }
        
        // Fill remaining slots with empty panels
        int remainingSlots = 20 - player.getInventory().size();
        for (int i = 0; i < remainingSlots; i++) {
            // Creates a button for each empty slot in the inventory
            JButton emptyButton = new JButton("Empty");
            // Adds the button to the inventory panel
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
