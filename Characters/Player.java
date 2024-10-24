import java.util.ArrayList;

public class Player extends Character {
    private ArrayList<Item> inventory;
    private Clothing clothingBottom;
    private Clothing clothingTop;
    private Weapon weapon;
    private int coins;

    public Player(String name, int health, int xCoord, int yCoord, int movementSpeed, Weapon weapon, Clothing clothingBottom, Clothing clothingTop, ArrayList<Item> inventory, int coins) {
        super(name, health, xCoord, yCoord, movementSpeed, weapon);
        this.inventory = inventory;
        this.coins = coins;
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public void collectItem(Item item) {
        inventory.add(item);
    }

    public void removeItem(Item item) {
        inventory.remove(item);
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public void removeCoins(int amount) {
        coins -= amount;
    }

    public void addCoins(int amount) {
        coins += amount;
    }
}

