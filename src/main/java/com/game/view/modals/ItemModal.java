package com.game.view.modals;

import javax.swing.JFrame;

import com.game.model.Items.Item;

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
    private InventoryModal inventoryModal;
    private Item item;
    private boolean visable = false;


    /**
     * Constructor for the ItemModal class when the player interacts with a merchant
     * which will allow them to buy and sell items
     * @param merchantModal
     * @param item
     */
    public ItemModal(MerchantModal merchantModal, Item item) {
        this.merchantModal = merchantModal;
        this.item = item;
    }

    /**
     * Constructor for the ItemModal class when the player interacts with an item in their inventory
     * which will allow them to use and interact with items
     * @param inventoryModal
     * @param item
     */
    public ItemModal(InventoryModal inventoryModal, Item item) {
        this.inventoryModal = inventoryModal;
        this.item = item;
        
    }

    // private void buildItemScreen() {

    // }

     /**
     * Returns the visibility of the item screen
     * @return
     */
    public boolean isVisible() {
        return visable;
    }
    
    /**
     * Shows the item screen
     */
    public void showItem() {
        setVisible(true);
        visable = true;
    }
    
    /**
     * Hides the item screen
     */
    public void hideItem() {
        setVisible(false);
        visable = false;
    }

}
