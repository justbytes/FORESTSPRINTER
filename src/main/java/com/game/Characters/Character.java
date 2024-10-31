package com.game.Characters;
import com.game.Items.subclasses.Weapon;

/**
 * Lead Author(s):
 * @author Jesse Marino
 * 
 * Version/date: 10-25-2024
 * 
 * Responsibilities of class:
 * Represents a character that can be in the game/World 
 */
public class Character {
    private int health;
    private int xCoord;
    private int yCoord;
    private int movementSpeed;
    private Weapon weapon;
    private String name;

    /**
     * Constructor for the Character class
     * @param name
     * @param health
     * @param xCoord
     * @param yCoord
     * @param movementSpeed
     * @param weapon
     */
    public Character(String name, int health, int xCoord, int yCoord, int movementSpeed, Weapon weapon) {
        this.name = name;
        this.health = health;
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.movementSpeed = movementSpeed;
        this.weapon = weapon;
    }

    /**
     * Get the name of the character
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Get the movement speed of the character
     * @return
     */
    public int getMovementSpeed() {
        return movementSpeed;
    }

    /**
     * Get the health of the character
     * @return
     */
    public int getHealth() {
        return health;
    }

    /**
     * Increase the health of the character
     * @param amount
     * @return
     */
    public int increaseHealth(int amount) {

        if (health + amount > 100) {
            health = 100;
        } else {
            health = health + amount;
        }
        return health;
    }

    /**
     * Decrease the health of the character
     * @param amount
     * @return
     */
    public int decreaseHealth(int amount) {
        if (health - amount < 0) {
            health = 0;
        } else {
            health = health - amount;
        }
        return health;
    }

    /**
     * Set the coordinates of the character
     * @param x
     * @param y
     */
    public void setCoords(int x, int y) {
        this.xCoord = x;
        this.yCoord = y;
    }

    /**
     * Get the coordinates of the character
     * @return
     */
    public int[] getCoords() {
        return new int[]{xCoord, yCoord};
    }

    /**
     * Move the character up
     */
    public void moveUp() {
        yCoord++;
    }

    /**
     * Move the character down
     */
    public void moveDown() {
        yCoord--;
    }

    /**
     * Move the character left
     */
    public void moveLeft() {
        xCoord--;
    }

    /**
     * Move the character right
     */
    public void moveRight() {
        xCoord++;
    }

    /**
     * Get the attack power of the character
     * @return
     */
    public int getAttackPower() {
        return weapon.getDamage();
    }

    /**
     * Get the weapon of the character
     * @return
     */
    public Weapon getWeapon() {
        return weapon;
    }

    /**
     * Set the weapon of the character
     * @param weapon
     */
    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }
}
