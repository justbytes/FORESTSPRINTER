package com.game.view.modals;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import com.game.controller.GameController;
import com.game.model.Characters.subclasses.Player;
import com.game.model.Items.Item;
import com.game.model.Merchants.Merchant;
import com.game.model.World;

/**
* 
 * Lead Author(s):
 * @author Jesse Marino
 * 
 * Version/date: 11-20-2024 11-22-2024
 * 
 * Responsibilities of class:
 * Creates the GUI modal for the merchant trading screen that can be displayed allows for buying and selling of items
 */

public class MerchantModal extends JFrame {
    // Instance variables
    private World world;
    private JPanel mainPanel;
    private JTabbedPane tabbedPane;
    private JPanel buyPanel;
    private JPanel sellPanel;
    private JLabel playerCoinsLabel;
    private JLabel merchantCoinsLabel;
    private Merchant merchant;
    private Player player;
    private boolean visible = false;
    private GameController gameController;
    /**
     * Constructor for the MerchantModal class
     * @param merchant
     * @param player
     */
    public MerchantModal(Merchant merchant, Player player, World world, GameController gameController) {
        this.merchant = merchant;
        this.player = player;
        this.world = world;
        this.gameController = gameController;

        // Set the JFrame properties
        setTitle("Trading with " + merchant.getName());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 500);
        setLocationRelativeTo(null);
        
        // Initialize the main panel
        mainPanel = new JPanel(new BorderLayout());
        
        // Create coins panel at the top
        JPanel coinsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        playerCoinsLabel = new JLabel("Your Coins: " + player.getCoins());
        merchantCoinsLabel = new JLabel("Merchant Coins: " + merchant.getCoins());
        coinsPanel.add(playerCoinsLabel);
        coinsPanel.add(new JLabel(" | "));
        coinsPanel.add(merchantCoinsLabel);
        
        // Add the coins panel to the main panel
        mainPanel.add(coinsPanel, BorderLayout.NORTH);
        
        // Initialize panels
        buyPanel = new JPanel();
        sellPanel = new JPanel();
        
        // Create tabbed pane
        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Buy Items", new JScrollPane(buyPanel));
        tabbedPane.addTab("Sell Items", new JScrollPane(sellPanel));
        
        // Add the tabbed pane to the main panel
        mainPanel.add(tabbedPane, BorderLayout.CENTER);

        // Add the main panel to the JFrame
        add(mainPanel);   
        
        // Create the buy panel
        createBuyPanel();   
        // Create the sell panel
        createSellPanel();

        // Add a listener to this window so that restarts the gameTimer in the gameController
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                gameController.startGame();
            }
        });
    }
    
    /**
     * Creates the buy panel which displays the items the merchant has for sale
     */
    private void createBuyPanel() {
        // Set the panel layout
        buyPanel.setLayout(new GridLayout(0, 2));
        
        // Get the merchant's inventory
        ArrayList<Item> merchantInventory = merchant.getInventory();
        
        // Loop through the merchant's inventory and add a button for each item
        for (Item item : merchantInventory) {
            JPanel itemPanel = new JPanel();
            JButton itemButton = new JButton(item.getName());

            // Add an action listener to the item button
            itemButton.addActionListener(e -> {
                ItemModal itemModal = new ItemModal(world, this, merchant, this.player, item, true);
                itemModal.setVisible(true);
            });
            
            // Add the items to the panels
            itemPanel.add(itemButton);
            buyPanel.add(itemPanel);
        }
    }
    
    /**
     * Creates the sell panel which displays the items the player has to sell
     */
    private void createSellPanel() {
        // Set the panel layout
        sellPanel.setLayout(new GridLayout(0, 2));
        
        // Get the player's inventory
        ArrayList<Item> playerInventory = player.getInventory();
        
        // If the player's inventory is empty, add a disabled button to the sell panel
        if (playerInventory.isEmpty()) {
            JButton emptyButton = new JButton("Your inventory is empty");
            emptyButton.setEnabled(false);
            sellPanel.add(emptyButton);
        } else {
            // Loop through the player's inventory and add a button for each item
            for (Item item : playerInventory) {
                JPanel itemPanel = new JPanel();
                JButton itemButton = new JButton(item.getName());

                // Add an action listener to the item button
                itemButton.addActionListener(e -> {
                   ItemModal itemModal = new ItemModal(world, this, merchant, player, item, false);
                   itemModal.setVisible(true);
                });
                
                // Add the items to panels
                itemPanel.add(itemButton);
                sellPanel.add(itemPanel);
            }
        }
    }
    
    /**
     * Updates the display of the merchant modal
     */
    public void updateDisplay() {
        // Update the player and merchant coins labels
        playerCoinsLabel.setText("Your Coins: " + player.getCoins());
        merchantCoinsLabel.setText("Merchant Coins: " + merchant.getCoins());
        
        // Refresh panels
        buyPanel.removeAll();
        sellPanel.removeAll();
        createBuyPanel();
        createSellPanel();
        
        // Refresh the display
        buyPanel.revalidate();
        sellPanel.revalidate();
        buyPanel.repaint();
        sellPanel.repaint();
    }
    
    /**
     * Checks if the merchant modal is visible
     * @return visible
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * Shows the merchant modal
     */
    public void showMerchant() {
        updateDisplay();
        setVisible(true);
        visible = true;
    }
    
    /**
     * Hides the merchant modal
     */
    public void hideMerchant() {
        setVisible(false);
        visible = false;
    }
}
