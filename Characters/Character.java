public class Character {
    private int health;
    private int xCoord;
    private int yCoord;
    private int movementSpeed;
    private Weapon weapon;
    private Clothing clothingTop;
    private Clothing clothingBottom;
    private String name;

    // Constructor
    public Character(String name, int health, int xCoord, int yCoord, int movementSpeed, Weapon weapon) {
        this.name = name;
        this.health = health;
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.movementSpeed = movementSpeed;
        this.weapon = weapon;
    }
    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int increaseHealth(int amount) {
        return health = health + amount;
    }

    public int decreaseHealth(int amount) {
        return health = health - amount;
    }

    public void setCoords(int x, int y) {
        this.xCoord = x;
        this.yCoord = y;
    }

    public int[] getCoords() {
        return new int[]{xCoord, yCoord};
    }

    public void attack() {
        // Implement
    }

    
}
