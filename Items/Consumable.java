public class Consumable extends Item {
    private int healingProps;

    public Consumable(String name, String description, double price, int x, int y, String status, int healingProps) {
        super(name, description, price, x, y, status);
        this.healingProps = healingProps;
    }

    public int getHealingProps() {
        return healingProps;
    }
}
