import java.util.ArrayList;

public class Merchant {
    private String name;
    private Item favoriteItem;
    private int xCoord;
    private int yCoord;
    private ArrayList<Item> inventory;
    private int coins;

    public Merchant(String name, int x, int y) {
        this.name = name;
        this.xCoord = x;
        this.yCoord = y;
        this.inventory = new ArrayList<>();
        this.coins = 1000; // Starting coins
    }

    public Item getFavoriteItem() {
        return favoriteItem;
    }

    public String getName() {
        return name;
    }

    public int[] getCoords() {
        return new int[]{xCoord, yCoord};
    }

    public ArrayList<Item> getInventory() {
        // Return a copy of the inventory
        return new ArrayList<Item>(inventory);
    }

    public void setInventory(ArrayList<Item> inventory) {
        this.inventory = new ArrayList<Item>(inventory);
    }

    public void buyItem(Item item) {
        if (coins >= item.getPrice()) {
            inventory.add(item);
            coins -= item.getPrice();
        }
    }

    public void sellItem(Item item) {
        if (inventory.remove(item)) {
            coins += item.getPrice();
        }
    }

    public void addCoins(int amount) {
        coins += amount;
    }

    public void removeCoins(int amount) {
        coins -= amount;
    }

    public int getCoins() {
        return coins;
    }
}
