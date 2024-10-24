public class Tool extends Item {
    private String utility;

    public Tool(String name, String description, double price, int x, int y, String status, String utility) {
        super(name, description, price, x, y, status);
        this.utility = utility; // "mining", "woodcutting", "fishing", "crafting"
    }

    public String getUtility() {
        return utility;
    }
}
