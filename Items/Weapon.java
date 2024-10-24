public class Weapon extends Item {
    private int damage;

    public Weapon(String name, String description, double price, int x, int y, String status, int damage) {
        super(name, description, price, x, y, status);
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }
}
