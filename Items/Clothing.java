public class Clothing extends Item {
    private int protection;
    private String type;

    public Clothing(String name, String description, double price, int x, int y, String status, int protection, String type) {
        super(name, description, price, x, y, status);
        this.protection = protection;
        this.type = type; // "bottom" or "top"
    }

    public int getProtection() {
        return protection;
    }

    public String getType() {
        return type;
    }
}
