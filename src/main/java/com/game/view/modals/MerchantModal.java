package com.game.view.modals;

import javax.swing.*;
import java.awt.*;
import com.game.model.Merchants.Merchant;
import com.game.model.Characters.subclasses.Player;
import com.game.model.Items.Item;
import java.util.ArrayList;
import java.util.concurrent.Flow;

/**
* 
 * Lead Author(s):
 * @author Jesse Marino
 * 
 * Version/date: 11-15-2024
 * 
 * Responsibilities of class:
 * GUI for the merchant trading screen that can be displayed by pressing F near a merchant
 * Extends JFrame
 */

public class MerchantModal extends JFrame {
    private JPanel mainPanel;
    private JTabbedPane tabbedPane;
    private JPanel buyPanel;
    private JPanel sellPanel;
    private JLabel playerCoinsLabel;
    private JLabel merchantCoinsLabel;
    private Merchant merchant;
    private Player player;
    private boolean visible = false;

    public MerchantModal(Merchant merchant, Player player) {
        this.merchant = merchant;
        this.player = player;
        
        setTitle("Trading with " + merchant.getName());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 500);
        setLocationRelativeTo(null);
        
        mainPanel = new JPanel(new BorderLayout());
        
        // Create coins panel at the top
        JPanel coinsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        playerCoinsLabel = new JLabel("Your Coins: " + player.getCoins());
        merchantCoinsLabel = new JLabel("Merchant Coins: " + merchant.getCoins());
        coinsPanel.add(playerCoinsLabel);
        coinsPanel.add(new JLabel(" | "));
        coinsPanel.add(merchantCoinsLabel);
        
        mainPanel.add(coinsPanel, BorderLayout.NORTH);
        
        // Initialize panels
        buyPanel = new JPanel();
        sellPanel = new JPanel();
        
        // Create tabbed pane
        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Buy Items", new JScrollPane(buyPanel));
        tabbedPane.addTab("Sell Items", new JScrollPane(sellPanel));
        
        mainPanel.add(tabbedPane, BorderLayout.CENTER);

        add(mainPanel);   
        
        createBuyPanel();   
        createSellPanel();
    }
    
    private void createBuyPanel() {
        
        buyPanel.setLayout(new GridLayout(0, 2));
        
        ArrayList<Item> merchantInventory = merchant.getInventory();
        System.out.println("Merchant inventory size: " + merchantInventory.size());
        
        for (Item item : merchantInventory) {
            JPanel itemPanel = new JPanel();
            
            JButton itemButton = new JButton(item.getName());
            itemButton.addActionListener(e -> {
                new ItemModal(this, item);
            });
            
            itemPanel.add(itemButton);
            buyPanel.add(itemPanel);
            
        }
    }
    
    private void createSellPanel() {
        
        sellPanel.setLayout(new GridLayout(0, 2));
        
        ArrayList<Item> playerInventory = player.getInventory();
        System.out.println("Player inventory size: " + playerInventory.size());
        
        if (playerInventory.isEmpty()) {
            JButton emptyButton = new JButton("Your inventory is empty");
            emptyButton.setEnabled(false);
            sellPanel.add(emptyButton);
        } else {
            for (Item item : playerInventory) {
                JPanel itemPanel = new JPanel();
                
                JButton itemButton = new JButton(item.getName());
                itemButton.addActionListener(e -> {
                    new ItemModal(this, item);
                });
                
                itemPanel.add(itemButton);
                sellPanel.add(itemPanel);
            }
        }
    }
    
    private void updateDisplay() {
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
    
    public boolean isVisible() {
        return visible;
    }
    
    public void showMerchant() {
        updateDisplay();
        setVisible(true);
        visible = true;
    }
    
    public void hideMerchant() {
        setVisible(false);
        visible = false;
    }
}
