package com.game.view.modals;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.game.model.Characters.subclasses.Player;
import com.game.model.Items.Item;
import com.game.model.Items.subclasses.Clothing;
import com.game.model.Items.subclasses.Consumable;
import com.game.model.Items.subclasses.Tool;
import com.game.model.Items.subclasses.Weapon;
import com.game.model.Merchants.Merchant;
import com.game.model.World;

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
    private World world;
    private MerchantModal merchantModal;
    private Merchant merchant;
    private Player player;
    private InventoryModal inventoryModal;
    private Item item;
    private JPanel mainPanel;
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
    public ItemModal(World world, MerchantModal merchantModal, Merchant merchant, Player player, Item item, boolean isMerchant) {
        this.world = world;
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
        
        // Build the GUI
        buildMerchantItemModal();

        // Pack the frame to fit the components and make it visible
        pack();  
        setVisible(true); 
    }

    /**
     * Constructor for when accessing a item through the players inventory
     * @param world
     * @param inventoryModal
     * @param player
     * @param item
     */
    public ItemModal(World world, InventoryModal inventoryModal, Player player, Item item) {
        this.world = world;
        this.inventoryModal = inventoryModal;
        this.player = player;
        this.item = item;

        // Setup the frame
        setTitle("Item Details");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 400);
        setLocationRelativeTo(null);
        
        // Build the GUI
        buildPlayerItemModal();

        // Pack the frame to fit the components and make it visible
        pack();  
        setVisible(true); 
    }

    /**
     * Builds the merchant item modal when the player interacts with a merchant
     */
    private void buildMerchantItemModal() {
        // Create the main panel
        mainPanel = new JPanel(new BorderLayout(10, 10));
    
        // Create item info panel
        JPanel infoPanel = handleItemPanel();
    
        // Add some padding around the info panel
        JPanel paddedInfoPanel = new JPanel(new BorderLayout());
        paddedInfoPanel.add(infoPanel, BorderLayout.NORTH);
    
        // Button panel at the bottom
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        // Create transaction button
        JButton transactionButton = new JButton(isMerchant ? "Buy" : "Sell");

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
    }

    /**
     * Builds the player item modal when the player accesses an item through their inventory
     */
    private void buildPlayerItemModal() {
        // Create the main panel
        mainPanel = new JPanel(new BorderLayout(10, 10));
    
        // Create item info panel
        JPanel infoPanel = handleItemPanel();
    
        // Add some padding around the info panel
        JPanel paddedInfoPanel = new JPanel(new BorderLayout());
        paddedInfoPanel.add(infoPanel, BorderLayout.NORTH);
    

        // Create drop item button
        JButton dropButton = new JButton("Drop");
        dropButton.addActionListener(e -> {
            // Removes the item from the player's inventory
            player.removeItem(item);

            // Unequips the item if it is equipped
            if (item.isEquipped()) {
                item.equip();
            }
            // Sets the item's coordinates to the player's coordinates
            int[] coords = player.getCoords();
            item.setCoords(coords);

            // updates the item to the world's consumables list
            if (item instanceof Consumable) {
                ArrayList<Consumable> consumables = world.getConsumables();
                consumables.add((Consumable) item);
                world.setConsumables(consumables);
            } else if (item instanceof Weapon) {
                ArrayList<Weapon> weapons = world.getWeapons();
                weapons.add((Weapon) item);
                world.setWeapons(weapons);
            } else if (item instanceof Clothing) {
                ArrayList<Clothing> clothing = world.getClothing();
                clothing.add((Clothing) item);
                world.setClothing(clothing);
            } else if (item instanceof Tool) {
                ArrayList<Tool> tools = world.getTools();
                tools.add((Tool) item);
                world.setTools(tools);
            } else {
                JOptionPane.showMessageDialog(null, "Unkown error when dropping the item");
            }
            inventoryModal.updateInventory();
            hideItem();
        });
        
        // Create equip item button | players can equip weapons and clothing
        JButton equipButton = new JButton(item.isEquipped() ? "Equipped" : "Equip");
        equipButton.addActionListener(e -> {
            if (item instanceof Weapon) {
                
                player.equipWeapon((Weapon) item);
                hideItem();
            } else if (item instanceof Clothing) {
                String type = ((Clothing) item).getType();
                if (type.equals("Top")) {
                    player.setClothingTop((Clothing) item);
                    hideItem();
                } else {
                    player.setClothingBottom((Clothing) item);
                    hideItem();
                }
            }
        });

        // Create consume item button
        JButton consumeButton = new JButton("Consume");
        consumeButton.addActionListener(e -> {
            player.useConsumable((Consumable) item);
            inventoryModal.updateInventory();
            hideItem();
            
        });

        // Button panel at the bottom
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        // Add the correct button based on the item type
        if (item instanceof Consumable) {
            buttonPanel.add(consumeButton);
        } else if (item instanceof Weapon) {
            buttonPanel.add(equipButton);
        } else if (item instanceof Clothing) {
            buttonPanel.add(equipButton);
        }
        
        // Addd all of the components to the main panel
        buttonPanel.add(dropButton);
        mainPanel.add(paddedInfoPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add the main panel to the frame
        add(mainPanel);
    }

    /**
     * Handles the item info panel
     * @return
     */
    public JPanel handleItemPanel() {
        // Create item info panel with vertical BoxLayout
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));

        // Create labels based on item instance type
        JLabel[] labels;
        if (item instanceof Weapon weapon) {
            labels = new JLabel[] {
                new JLabel("Name: " + item.getName()),
                new JLabel("Description: " + item.getDescription()),
                new JLabel("Damage: " + weapon.getDamage()),
                new JLabel("Status: " + item.getStatus()),
                new JLabel("Equiped: " + weapon.isEquipped()),
                new JLabel("Price: " + item.getPrice())
            };
        } else if (item instanceof Clothing clothing) {
            labels = new JLabel[] {
                new JLabel("Name: " + item.getName()),
                new JLabel("Description: " + item.getDescription()),
                new JLabel("Protection: " + clothing.getProtection()),
                new JLabel("Type: " + clothing.getType()),
                new JLabel("Status: " + item.getStatus()),
                new JLabel("Equiped: " + clothing.isEquipped()),
                new JLabel("Price: " + item.getPrice())
            };
        } else if (item instanceof Tool tool) {
            labels = new JLabel[] {
                new JLabel("Name: " + item.getName()),
                new JLabel("Description: " + item.getDescription()),
                new JLabel("Utility: " + tool.getUtility()),
                new JLabel("Status: " + item.getStatus()),
                new JLabel("Price: " + item.getPrice())
            };
        } else if (item instanceof Consumable consumable) {
            labels = new JLabel[] {
                new JLabel("Name: " + item.getName()),
                new JLabel("Description: " + item.getDescription()),
                new JLabel("Healing Prop: " + consumable.getHealingProps()),
                new JLabel("Status: " + item.getStatus()),
                new JLabel("Price: " + item.getPrice())
            };
        } else {
            labels = new JLabel[0];
        }

        // Add labels with center alignment and padding
        for (JLabel label : labels) {
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            infoPanel.add(Box.createVerticalStrut(10)); 
            infoPanel.add(label);
        }
        
        // Add padding at the bottom
        infoPanel.add(Box.createVerticalStrut(10));

        return infoPanel;
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
