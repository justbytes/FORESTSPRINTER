package com.game.view.modals;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.game.model.Characters.subclasses.Player;
import com.game.model.Items.Item;
import com.game.model.Merchants.Merchant;

/**
 * Lead Author(s):
 * @author Jesse Marino
 * 
 * Version/date: 11-21-2024 11-22-2024
 * 
 * Responsibilities of class:
 * Creates the GUI modal for the player's item screen where they will be able to view and manage their item by dropping, equipping, or using it
 * If the ItemModal is created in the MerchantModal, the player will additionally be ableto buy and sell items
 */

public class ItemModal extends JFrame {
    // Instance variables
    private MerchantModal merchantModal;
    private Merchant merchant;
    private Player player;
    private InventoryModal inventoryModal;
    private Item item;
    private JPanel mainPanel;
    private JButton transactionButton;
    private boolean isMerchant;

    /**
     * Constructor for the ItemModal class when the player interacts with a merchant
     * which will allow them to buy and sell items
     * @param merchantModal
     * @param merchant
     * @param player
     * @param item
     * @param isMerchant
     */
    public ItemModal(MerchantModal merchantModal, Merchant merchant, Player player, Item item, boolean isMerchant) {
        this.merchantModal = merchantModal;
        this.merchant = merchant;
        this.player = player;
        this.item = item;
        this.isMerchant = isMerchant;

        // Setup the frame
        setTitle("Item Details");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 400);
        setLocationRelativeTo(null);
        
        // Create the main panel
        mainPanel = new JPanel(new BorderLayout(10, 10));
    
        // Create item info panel
        JPanel infoPanel = new JPanel(new GridLayout(4, 1, 5, 5));
        infoPanel.add(new JLabel("Name: \n" + item.getName()));
        infoPanel.add(new JLabel("Description: \n" + item.getDescription()));
        infoPanel.add(new JLabel("Status: \n" + item.getStatus()));
        infoPanel.add(new JLabel("Price: \n" + item.getPrice()));
    
        // Add some padding around the info panel
        JPanel paddedInfoPanel = new JPanel(new BorderLayout());
        paddedInfoPanel.add(infoPanel, BorderLayout.NORTH);
    
        // Button panel at the bottom
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        // Create transaction button
        transactionButton = new JButton(isMerchant ? "Buy" : "Sell");

        // Create the correct transaction button listener, True means the merchant is buying an item from the player
        if (isMerchant) {
            transactionButton.addActionListener(e -> {
                boolean success = merchant.buyItem(player, item);
                if (success) {
                    merchantModal.updateDisplay();
                    hideItem();
                } else {

                    if (player.getCoins() < item.getPrice()) {
                        JOptionPane.showMessageDialog(null, "You don't have enough coins to buy item " + item.getName());
                    } else if (player.getInventory().size() >= 20) {
                        JOptionPane.showMessageDialog(null, "Your inventory is full! Try selling or dropping some items");
                    } else {
                        JOptionPane.showMessageDialog(null, "Unkown error when buying the item from the merchant");
                    }
                }
            });
        } else {
            // The player is selling an item to the merchant
            transactionButton.addActionListener(e -> {
                boolean success = merchant.sellItem(player, item);
                if (success) {
                    merchantModal.updateDisplay();
                    hideItem();
                } else {

                    if (merchant.getCoins() < item.getPrice()) {
                        JOptionPane.showMessageDialog(null, "Merchant doesn't have enough coins to buy item " + item.getName());
                    } else  {
                        JOptionPane.showMessageDialog(null, "Unkown error when selling the item to the merchant");
                    }
                }
            });
        }
        
        // Add components to the main panel
        buttonPanel.add(transactionButton);
        mainPanel.add(paddedInfoPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add the main panel to the frame
        add(mainPanel);

        // Pack the frame to fit the components and make it visible
        pack();  
        setVisible(true); 
    }

    /**
     * Shows the item screen
     */
    public void showItem() {
        setVisible(true);
    }
    
    /**
     * Hides the item screen
     */
    public void hideItem() {
        setVisible(false);
        dispose();
    }

}
