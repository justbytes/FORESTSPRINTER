package com.game.view;

import javax.swing.*;
import java.awt.*;
import com.game.model.Merchants.Merchant;
import com.game.model.Characters.subclasses.Player;
import com.game.model.Items.Item;
import java.util.ArrayList;

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

public class MerchantScreen extends JFrame {
    private JPanel mainPanel;
    private JTabbedPane tabbedPane;
    private JPanel buyPanel;
    private JPanel sellPanel;
    private JLabel playerCoinsLabel;
    private JLabel merchantCoinsLabel;
    private Merchant merchant;
    private Player player;
    private boolean visible = false;

    public MerchantScreen(Merchant merchant, Player player) {
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
        
        // Create the content
        createBuyPanel();
        createSellPanel();
    }
    
    private void createBuyPanel() {
        buyPanel = new JPanel();
        buyPanel.setLayout(new GridLayout(5, 4, 10, 10)); // Single column, multiple rows with gaps
        
        ArrayList<Item> merchantInventory = merchant.getInventory();
        System.out.println("Merchant inventory size: " + merchantInventory.size());
        
        if (merchantInventory.isEmpty()) {
            JButton emptyButton = new JButton("Empty");
            emptyButton.setEnabled(false);
            buyPanel.add(emptyButton);
        } else {
            for (Item item : merchantInventory) {
                JPanel itemPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                
                JButton itemButton = new JButton(item.getName() + " - " + item.getPrice() + " coins");
                itemButton.addActionListener(e -> {
                    merchant.sellItem(player, item);
                    updateDisplay();
                });
                
                itemPanel.add(itemButton);
                buyPanel.add(itemPanel);
                mainPanel.add(buyPanel);
            }
        }
    }
    
    private void createSellPanel() {
        sellPanel = new JPanel();
        sellPanel.setLayout(new GridLayout(5, 4, 10, 10)); // Single column, multiple rows with gaps
        
        ArrayList<Item> playerInventory = player.getInventory();
        System.out.println("Player inventory size: " + playerInventory.size());
        
        if (playerInventory.isEmpty()) {
            JButton emptyButton = new JButton("Your inventory is empty");
            emptyButton.setEnabled(false);
            sellPanel.add(emptyButton);
        } else {
            for (Item item : playerInventory) {
                JPanel itemPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                
                JButton itemButton = new JButton(item.getName() + " - " + (item.getPrice() / 2) + " coins");
                itemButton.addActionListener(e -> {
                    merchant.buyItem(player, item);
                    updateDisplay();
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
