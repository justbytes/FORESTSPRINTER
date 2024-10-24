public class Animal extends Character {
    // Attributes
    private boolean rabid;

    // Constructor
    public Animal(String name, int health, int xCoord, int yCoord, int movementSpeed, Weapon weapon, boolean rabid) {
        super(name, health, xCoord, yCoord, movementSpeed, weapon);
        this.rabid = rabid;
    }

    public boolean isRabid() {
        return rabid;
    }

    public void setRabid(boolean rabid) {
        this.rabid = rabid;
    }
}

