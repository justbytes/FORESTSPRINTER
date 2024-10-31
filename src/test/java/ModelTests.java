
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

import com.game.Characters.Character;
import com.game.Characters.subclasses.Animal;
import com.game.Characters.subclasses.Player;
import com.game.Items.Item;
import com.game.Items.subclasses.Clothing;
import com.game.Items.subclasses.Consumable;
import com.game.Items.subclasses.Tool;
import com.game.Items.subclasses.Weapon;
import com.game.Merchants.Merchant;

/**
 * Lead Author(s):
 * @author Jesse Marino
 * 
 * Version/date: 10-25-2024
 * 
 * Responsibilities of class:
 * Tests the classes in the project
 */
class ModelTests
{

	/**
	 * Tests the Item class and its subclasses Clothing, Consumable, Weapon, and Tool
	 */
	@Test
	void itemTests()
	{
        
		Item item = new Item("Eye glass", "An item you can look through", 10, 100, 30, "Rare");
        // Data tests
        assertEquals("Eye glass", item.getName());
		assertEquals("An item you can look through", item.getDescription());
		assertEquals(10.0, item.getPrice());
		assertEquals(100, item.getCoords()[0]);
		assertEquals(30, item.getCoords()[1]);
		assertEquals("Rare", item.getStatus());
        

        // Clothing tests   
        Clothing clothing = new Clothing("Shirt", "A shirt you can wear", 10, 100, 30, "Common", 10, "top");
        assertEquals(10, clothing.getProtection());
        assertEquals("top", clothing.getType());

        // Consumable tests
        Consumable consumable = new Consumable("Bandage", "A bandage you can use to heal", 10, 100, 30, "Common", 10);
        assertEquals(10, consumable.getHealingProps());

        // Weapon tests
        Weapon weapon = new Weapon("Sword", "A sword you can use to fight", 10, 100, 30, "Common", 50);
        assertEquals(50, weapon.getDamage());

        // Tool tests   
        Tool tool = new Tool("Axe", "A tool you can use to chop wood", 10, 100, 30, "Common", "woodcutting");
        assertEquals("woodcutting", tool.getUtility());
	}

	/**
	 * Tests the Character class and its subclasses Player and Animal
	 */
    @Test
    void characterTests() {

        // Create objects needed for testing
        Clothing shirt = new Clothing("Shirt", "A shirt you can wear", 10, 100, 30, "Common", 10, "top");
        Clothing pants = new Clothing("Pants", "Pants you can wear", 10, 100, 30, "Common", 10, "bottom");
        Weapon sword = new Weapon("Sword", "A sword you can use to fight", 10, 100, 30, "Common", 50);
        Weapon bite = new Weapon("Bite", "Animals bite", 10, 100, 30, "NA", 10);
        Tool axe = new Tool("Axe", "A tool you can use to chop wood", 10, 100, 30, "Common", "woodcutting");
        Consumable apple = new Consumable("Apple", "An apple you can eat", 10, 100, 30, "Common", 10);
        Character character = new Character("player", 100, 100, 100, 25, sword);

        // Data tests
        assertEquals("player", character.getName());
        assertEquals(25, character.getMovementSpeed());
        assertEquals(100, character.getCoords()[0]);
        assertEquals(100, character.getCoords()[1]);
        assertEquals(sword, character.getWeapon());
        assertEquals(50, character.getAttackPower());

        // Movement tests
        character.moveUp();
        assertEquals(101, character.getCoords()[1]);
        character.moveDown();
        assertEquals(100, character.getCoords()[1]);
        character.moveLeft();
        assertEquals(99, character.getCoords()[0]);

        // Health tests
        assertEquals(100, character.getHealth());
        assertEquals(100, character.increaseHealth(0));
        assertEquals(90, character.decreaseHealth(10));
        assertEquals(100, character.increaseHealth(10));

        // Create a testing inventory for the player
        ArrayList<Item> inventory = new ArrayList<Item>();
        inventory.add(apple);
        inventory.add(axe);

        // Player class tests
        Player player = new Player("player", 100, 100, 100, 25, sword, pants, shirt, inventory, 25);
        Clothing shirt01 = new Clothing("Leather Shirt", "A shirt you can wear", 10, 100, 30, "Rare", 15, "top");
        Clothing pants01 = new Clothing("Leather Pants", "Pants you can wear", 10, 100, 30, "Rare", 15, "bottom");

        // Clothing tests
        assertEquals(shirt, player.getClothingTop());
        assertEquals(pants, player.getClothingBottom());
        player.setClothingTop(shirt01);
        player.setClothingBottom(pants01);
        assertEquals(shirt01, player.getClothingTop());
        assertEquals(pants01, player.getClothingBottom());
        
        // Inventory tests
        assertEquals(inventory, player.getInventory());
        assertEquals(true, player.collectItem(apple));
        assertEquals(3, player.getInventory().size());
        player.removeItem(apple);
        player.removeItem(axe);
        assertEquals(1, player.getInventory().size());  
        player.decreaseHealth(40);
        assertEquals(90, player.getHealth());
        player.useConsumable(apple);
        assertEquals(100, player.getHealth());
        assertEquals(0, player.getInventory().size());

        // Coins tests
        assertEquals(25, player.getCoins());
        player.addCoins(10);
        assertEquals(35, player.getCoins());
        player.removeCoins(5);
        assertEquals(30, player.getCoins());
        player.removeCoins(35);
        assertEquals(0, player.getCoins());

        // Change Weapon tests
        Weapon newWeapon = new Weapon("War Axe", "A tool you can use to chop wood", 10, 100, 30, "Common", 55);
        assertEquals(true, player.equipWeapon(newWeapon));
        assertEquals(newWeapon, player.getWeapon());

        // Animal tests
        Animal animal = new Animal("Bear", 100, 100, 100, 25, bite, false);
        animal.decreaseHealth(player.getAttackPower());
        assertEquals(45, animal.getHealth());
        assertEquals(false, animal.isRabid());
        animal.setRabid(true);
        assertEquals(true, animal.isRabid());
    }

    @Test
    void merchantTests() {

        // Items for merchant testing
        Clothing shirt = new Clothing("Shirt", "A shirt you can wear", 10, 100, 30, "Common", 10, "top");
        Clothing pants = new Clothing("Pants", "Pants you can wear", 10, 100, 30, "Common", 10, "bottom");
        Weapon sword = new Weapon("Sword", "A sword you can use to fight", 10, 100, 30, "Common", 50);
        Weapon bite = new Weapon("Bite", "Animals bite", 10, 100, 30, "NA", 10);
        Tool axe = new Tool("Axe", "A tool you can use to chop wood", 10, 100, 30, "Common", "woodcutting");
        Consumable apple = new Consumable("Apple", "An apple you can eat", 10, 100, 30, "Common", 10);

        // Player setup
        ArrayList<Item> playerInventory = new ArrayList<Item>();
        playerInventory.add(pants);
        playerInventory.add(apple);
        playerInventory.add(axe);
        Player player = new Player("player", 100, 100, 100, 25, sword, pants, shirt, playerInventory, 25);

        // Merchant setup
        ArrayList<Item> inventory = new ArrayList<Item>();
        inventory.add(shirt);
        inventory.add(pants);
        inventory.add(sword);
        inventory.add(bite);
        inventory.add(axe);
        inventory.add(apple);
        Merchant merchant = new Merchant("Steve", 100, 100, inventory, Clothing.class);

        // Data tests
        assertEquals(inventory, merchant.getInventory());
        assertEquals(1000, merchant.getCoins());
        assertEquals(Clothing.class, merchant.getFavoriteItemType());
        assertEquals("Steve", merchant.getName());
        assertEquals(100, merchant.getCoords()[0]);
        assertEquals(100, merchant.getCoords()[1]);

        // Buy Item from player
        assertEquals(inventory, merchant.getInventory());
        assertEquals(true, merchant.buyItem(player, playerInventory.get(0)));
        assertEquals(1000 - (int) (playerInventory.get(0).getPrice() * 1.3), merchant.getCoins());
        assertEquals(25 + (int) (playerInventory.get(0).getPrice() * 1.3), player.getCoins());
        assertEquals(7, merchant.getInventory().size());
        assertEquals(2, player.getInventory().size());

        // Sell Item to player
        merchant.sellItem(player, merchant.getInventory().get(0));
        assertEquals(1000 - (int) (playerInventory.get(0).getPrice() * 1.3) + 10, merchant.getCoins());
        assertEquals(25 + (int) (playerInventory.get(0).getPrice() * 1.3) - 10, player.getCoins());
        assertEquals(6, merchant.getInventory().size());
        assertEquals(3, player.getInventory().size());
    }
}
