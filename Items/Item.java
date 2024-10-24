public class Item {
    // Attributes
    private String status;
    private String description;
    private int xCoords;
    private int yCoords;
    private double price;
    private String name;

    // Constructor
    public Item(String name, String description, double price, int x, int y, String status) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.xCoords = x;
        this.yCoords = y;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }
    public int[] getCoords() {
        return new int[]{xCoords, yCoords};
    }

    public String getStatus() {
        return status;
    }
}
