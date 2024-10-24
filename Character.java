public class Character {

    String name;
    int health;
    int attackPower;

    Character(String name, int health) {
        this.name = name;
        this.health = health;
      
    }

    public static void main(String[] args) {
        Character character = new Character("John", 100);
        System.out.println(character.name);
    }
}
