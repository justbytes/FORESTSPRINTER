package com.game.model;

import com.game.model.Characters.subclasses.Animal;
import com.game.model.Characters.subclasses.Player;
import com.game.model.Items.Item;
import com.game.model.Items.subclasses.Clothing;
import com.game.model.Items.subclasses.Consumable;
import com.game.model.Items.subclasses.Tool;
import com.game.model.Items.subclasses.Weapon;
import com.game.model.Merchants.Merchant;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;
/**
* 
 * Lead Author(s):
 * @author Jesse Marino
 * 
 * Version/date: 10-30-2024 - 10-31-2024
 *               11-04-2024 - 11-08-2024
 * 
 * Responsibilities of class:
 * Handles the world state and keeps track of game data
 * 
 */
public class World {
    // Instance variables
    private ArrayList<Weapon> weapons;
    private ArrayList<Clothing> clothing;
    private ArrayList<Consumable> consumables;
    private ArrayList<Tool> tools;
    private ArrayList<Animal> animals;
    private ArrayList<Merchant> merchants;
    private Player player;
    
    /**
     * Constructor for the World class, creates all of the game objects on initialization
     * @param playerName
     */
    public World(String playerName) {
        populateWeapons();
        populateClothing();
        populateConsumables();
        populateTools();
        populateAnimals();
        populateMerchants();
        populatePlayer(playerName);

    }

    /**
     * Populates the weapons using data from the weapons.txt file
     */
    public void populateWeapons() {

        // Create a new ArrayList to hold the weapons
        weapons = new ArrayList<Weapon>();

        // Create a new Scanner to read the weapons.txt file
        Scanner scan = null;
        
        // Get the weapons from the weapons.txt file
        try {
            // Get input stream from resources and create Scanner
            InputStream inputStream = getClass().getResourceAsStream("/weapons.txt");
            scan = new Scanner(inputStream);
            
            // Initialize variables to hold weapon data
            String name = null;
            String description = null;
            int price = 0;
            int x = 0;
            int y = 0;
            String status = null;
            int damage = 0;
            
            // Read each line of the file and assign the data to the variables
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                if (line.startsWith("Name: ")) {
                    name = line.substring(6).trim();
                } else if (line.startsWith("Description: ")) {
                    description = line.substring(13).trim();
                } else if (line.startsWith("Price: ")) {
                    price = Integer.parseInt(line.substring(7).trim());
                } else if (line.startsWith("X: ")) {
                    x = Integer.parseInt(line.substring(3).trim());
                } else if (line.startsWith("Y: ")) {
                    y = Integer.parseInt(line.substring(3).trim());
                } else if (line.startsWith("Status: ")) {
                    status = line.substring(8).trim();
                } else if (line.startsWith("Damage: ")) {
                    damage = Integer.parseInt(line.substring(8).trim());
                    
                    // Create a new Weapon object and add it to the weapons ArrayList
                    weapons.add(new Weapon(name, description, price, x, y, status, damage));
                }
            }
            
        } catch (Exception e) {
            System.err.println("Error loading weapons: " + e.getMessage());
        } finally {
            // Close the Scanner
            if (scan != null) {
                scan.close();
            }
        }
    }

    /** 
     * Populates the clothing
     */
    public void populateClothing() {
        // Create a new ArrayList to hold the clothing
        clothing = new ArrayList<Clothing>();

        // Create a new Scanner to read the clothing.txt file
        Scanner scan = null;
        
        // Get the clothing from the clothing.txt file
        try {
            // Get input stream from resources and create Scanner
            InputStream inputStream = getClass().getResourceAsStream("/clothing.txt");
            scan = new Scanner(inputStream);
            
            // Initialize variables to hold clothing data
            String name = null;
            String description = null;
            int price = 0;
            int x = 0;
            int y = 0;
            String status = null;
            int defense = 0;
            String type = null;
            
            // Read each line of the file and assign the data to the variables
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                if (line.startsWith("Name: ")) {
                    name = line.substring(6).trim();
                } else if (line.startsWith("Description: ")) {
                    description = line.substring(13).trim();
                } else if (line.startsWith("Price: ")) {
                    price = Integer.parseInt(line.substring(7).trim());
                } else if (line.startsWith("X: ")) {
                    x = Integer.parseInt(line.substring(3).trim());
                } else if (line.startsWith("Y: ")) {
                    y = Integer.parseInt(line.substring(3).trim());
                } else if (line.startsWith("Status: ")) {
                    status = line.substring(8).trim();
                } else if (line.startsWith("Protection: ")) {
                    defense = Integer.parseInt(line.substring(12).trim());
                } else if (line.startsWith("Type: ")) {
                    type = line.substring(6).trim();
                    
                    // Create a new Clothing object and add it to the clothing ArrayList
                    clothing.add(new Clothing(name, description, price, x, y, status, defense, type));
                }
            }
            
        } catch (Exception e) {
            System.err.println("Error loading clothing: " + e.getMessage());
        } finally {
            // Close the Scanner
            if (scan != null) {
                scan.close();
            }
        }
    }

    /**
     * Populates the consumables using data from the consumables.txt file
     */
    public void populateConsumables() {
        // Create a new ArrayList to hold the consumables
        consumables = new ArrayList<Consumable>();

        // Create a new Scanner to read the consumables.txt file
        Scanner scan = null;
        
        // Get the consumables from the consumables.txt file
        try {
            // Get input stream from resources and create Scanner
            InputStream inputStream = getClass().getResourceAsStream("/consumables.txt");
            scan = new Scanner(inputStream);
            
            // Initialize variables to hold consumable data
            String name = null;
            String description = null;
            int price = 0;
            int x = 0;
            int y = 0;
            String status = null;
            int healAmount = 0;
            
            // Read each line of the file and assign the data to the variables
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                if (line.startsWith("Name: ")) {
                    name = line.substring(6).trim();
                } else if (line.startsWith("Description: ")) {
                    description = line.substring(13).trim();
                } else if (line.startsWith("Price: ")) {
                    price = Integer.parseInt(line.substring(7).trim());
                } else if (line.startsWith("X: ")) {
                    x = Integer.parseInt(line.substring(3).trim());
                } else if (line.startsWith("Y: ")) {
                    y = Integer.parseInt(line.substring(3).trim());
                } else if (line.startsWith("Status: ")) {
                    status = line.substring(8).trim();
                } else if (line.startsWith("HealingProps: ")) {
                    healAmount = Integer.parseInt(line.substring(14).trim());
    
                    // Create a new Consumable object and add it to the consumables ArrayList
                    consumables.add(new Consumable(name, description, price, x, y, status, healAmount));
                }
            }
            
        } catch (Exception e) {
            System.err.println("Error loading consumables: " + e.getMessage());
        } finally {
            if (scan != null) {
                scan.close();
            }
        }
    }

    /**
     * Populates the tools using data from the tools.txt file
     */
    public void populateTools() {
        // Create a new ArrayList to hold the tools
        tools = new ArrayList<Tool>();

        // Create a new Scanner to read the tools.txt file
        Scanner scan = null;
        
        // Get the tools from the tools.txt file
        try {
            // Get input stream from resources and create Scanner
            InputStream inputStream = getClass().getResourceAsStream("/tools.txt");
            scan = new Scanner(inputStream);
            
            // Initialize variables to hold tool data
            String name = null;
            String description = null;
            int price = 0;
            int x = 0;
            int y = 0;
            String status = null;
            String toolType = null;
            
            // Read each line of the file and assign the data to the variables
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                if (line.startsWith("Name: ")) {
                    name = line.substring(6).trim();
                } else if (line.startsWith("Description: ")) {
                    description = line.substring(13).trim();
                } else if (line.startsWith("Price: ")) {
                    price = Integer.parseInt(line.substring(7).trim());
                } else if (line.startsWith("X: ")) {
                    x = Integer.parseInt(line.substring(3).trim());
                } else if (line.startsWith("Y: ")) {
                    y = Integer.parseInt(line.substring(3).trim());
                } else if (line.startsWith("Status: ")) {
                    status = line.substring(8).trim();
                } else if (line.startsWith("Utility: ")) {
                    toolType = line.substring(9).trim();
                    
                    // Create a new Tool object and add it to the tools ArrayList
                    tools.add(new Tool(name, description, price, x, y, status, toolType));
                }
            }
            
        } catch (Exception e) {
            System.err.println("Error loading tools: " + e.getMessage());
        } finally {
            // Close the Scanner
            if (scan != null) {
                scan.close();
            }
        }
    }

    /**
     * Populates the animals using data from the animals.txt file
     */
    public void populateAnimals() {
        // Create a new ArrayList to hold the animals
        animals = new ArrayList<Animal>();

        // Create a new Scanner to read the animals.txt file
        Scanner scan = null;
        
        // Get the animals from the animals.txt file
        try {
            // Get input stream from resources and create Scanner
            InputStream inputStream = getClass().getResourceAsStream("/animals.txt");
            scan = new Scanner(inputStream);
            
            // Initialize variables to hold animal data
            String name = null;
            int health = 0;
            int x = 0;
            int y = 0;
            int movementSpeed = 0;
            boolean rabid = false;
            
            // Read each line of the file and assign the data to the variables
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                if (line.startsWith("Name: ")) {
                    name = line.substring(6).trim();
                } else if (line.startsWith("Health: ")) {
                    health = Integer.parseInt(line.substring(8).trim());
                } else if (line.startsWith("X: ")) {
                    x = Integer.parseInt(line.substring(3).trim());
                } else if (line.startsWith("Y: ")) {
                    y = Integer.parseInt(line.substring(3).trim());
                } else if (line.startsWith("Movement Speed: ")) {
                    movementSpeed = Integer.parseInt(line.substring(15).trim());
                } else if (line.startsWith("Rabid: ")) {
                    rabid = Boolean.parseBoolean(line.substring(7).trim());
                    
                    // Create a new Weapon object
                    Weapon weapon = new Weapon("Bite", "A bite from an animal", 0, 0, 0, "Common", 15);
                    
                    // Create a new Animal object
                    Animal animal = new Animal(name, health, x, y, movementSpeed, rabid);
                    
                    // Equip the weapon
                    animal.setWeapon(weapon);
                    
                    // Create a new Animal object and add it to the animals ArrayList
                    animals.add(animal);
                    
                }
            }
            
        } catch (Exception e) {
            System.err.println("Error loading animals: " + e.getMessage());
        } finally {
            if (scan != null) {
                scan.close();
            }
        }
    }

    /**
     * Creates a player with a basic starting inventory, weapon, and clothing set
     */
    public void populatePlayer(String playerName) {
        // Create a starting inventory
        ArrayList<Item> inventory = new ArrayList<Item>();
        Tool playerTool = new Tool("Fishing Rod", "A long rod with a hook and a line.", 10, 0, 0, "Common", "Fishing");
        Consumable apple = new Consumable("Apple", "An apple that heals 15 health.", 50, 0, 0, "Uncommon", 15);
        Clothing clothShirt = new Clothing("Cloth Shirt", "A basic cloth shirt", 5, 0, 0, "Common", 5, "Top");
        Clothing clothPants = new Clothing("Cloth Pants", "A basic cloth pants", 5, 0, 0, "Common", 5, "Bottom");
        Weapon playerWeapon = new Weapon("Dagger", "A small, sharp dagger with a light handle.", 10, 0, 0, "Common", 5);
       
        // Add the items to the inventory
        inventory.add(playerTool);
        inventory.add(apple);
        inventory.add(clothShirt);
        inventory.add(clothPants);
        inventory.add(playerWeapon);

        // Create the player 
        player = new Player(playerName, 100, 300, 400, 10, inventory, 15);

        // Equip the the gear
        player.equipWeapon((Weapon) inventory.get(4));
        player.setClothingTop((Clothing) inventory.get(2));
        player.setClothingBottom((Clothing) inventory.get(3));
    }

    /**
     * Populates the merchants
     */
    public void populateMerchants() {
        // Create the merchants inventory lists
        ArrayList<Item> barbraInventory = new ArrayList<Item>(this.consumables);
        ArrayList<Item> carterInventory = new ArrayList<Item>(this.clothing);
        ArrayList<Item> sampsonInventory = new ArrayList<Item>(this.tools);
        ArrayList<Item> victorInventory = new ArrayList<Item>(this.weapons);

        // Create the merchants list
        merchants = new ArrayList<Merchant>();

        // Create the merchants
        Merchant Barbra = new Merchant("Barbra", 100, 100, barbraInventory, Consumable.class);
        Merchant Carter = new Merchant("Carter", 1600, 100, carterInventory, Clothing.class);
        Merchant Sampson = new Merchant("Sampson", 1600, 1000, sampsonInventory, Tool.class);
        Merchant Victor = new Merchant("Victor", 100, 1000, victorInventory, Weapon.class);  

        // Add the merchants to the merchants ArrayList
        merchants.add(Barbra);
        merchants.add(Carter);
        merchants.add(Sampson);
        merchants.add(Victor);
    }
    

    /** 
     * Get the player
     * @return
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Get a list of weapons that are on the world
     * @return
     */
    public ArrayList<Weapon> getWeapons() {
        return new ArrayList<Weapon>(weapons);
    }

    /**
     * Set the weapons
     * @param weapons
     */
    public void setWeapons(ArrayList<Weapon> weapons) {
        this.weapons = weapons;
    }

    /**
     * Get a list of clothing that are on the world
     * @return
     */
    public ArrayList<Clothing> getClothing() {
        return new ArrayList<Clothing>(clothing);
    }

    /**
     * Set the clothing
     * @param clothing
     */
    public void setClothing(ArrayList<Clothing> clothing) {
        this.clothing = clothing;
    }

    /**
     * Get a list of consumables that are on the world
     * @return
     */
    public ArrayList<Consumable> getConsumables() {
        return new ArrayList<Consumable>(consumables);
    }   

    /**
     * Set the consumables
     * @param consumables
     */
    public void setConsumables(ArrayList<Consumable> consumables) {
        this.consumables = consumables;
    }

    /**
     * Get a list of tools that are on the world
     * @return
     */
    public ArrayList<Tool> getTools() {
        return new ArrayList<Tool>(tools);
    }   

    /**
     * Set the tools
     * @param tools
     */
    public void setTools(ArrayList<Tool> tools) {
        this.tools = tools;
    }

    /**
     * Get a list of animals that are on the world
     * @return
     */
    public ArrayList<Animal> getAnimals() {
        return new ArrayList<Animal>(animals);
    }   

    /**
     * Set the animals
     * @param animals
     */
    public void setAnimals(ArrayList<Animal> animals) {
        this.animals = animals;
    }

    /**
     * Get a list of merchants that are on the world
     * @return
     */
    public ArrayList<Merchant> getMerchants() {
        return new ArrayList<Merchant>(merchants);
    }   
}

