package com.game.view.modals;

import javax.swing.JFrame;

import com.game.model.Items.Item;

public class ItemModal extends JFrame {
    private MerchantModal merchantModal;
    private Item item;
    private boolean visable = false;

    public ItemModal(MerchantModal merchantModal, Item item) {
        this.merchantModal = merchantModal;
        this.item = item;
    }

    private void buildItemScreen() {

    }

    //TODO 

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
