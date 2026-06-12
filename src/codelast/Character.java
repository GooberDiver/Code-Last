/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codelast;

/**
 *
 * @author Goober
 */

// Importing
import processing.core.PApplet;
import processing.core.PImage;

public class Character {
    // Attributes
    public int x; // X and Y were set to public in order to use collision
    public int y;
    public int speed;
    private String name;
    private PApplet app;
    private PImage image;
    public HeldItem item; // If character needs to carry an item
    
    /**
     * Constructor for character class
     * @param p Sets up object to have visual
     * @param x horizontal coordinate
     * @param y vertical coordinate
     * @param speed move speed
     * @param name name of character
     * @param imagePath character image loads from imagePath
     */
    public Character (PApplet p, int x, int y, int speed, String name, String imagePath) {
        app = p;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.name = name;
        image = app.loadImage(imagePath);
    }
    
    /**
     * Alternate constructor which uses HeldItem
     * @param p sets up object to have visual
     * @param x horizontal coordinate
     * @param y vertical coordinate
     * @param imagePath object image loads from imagePath
     */
    public Character (PApplet p, int x, int y, String imagePath) { 
        app = p;
        this.x = x;
        this.y = y;
        image = app.loadImage(imagePath);
        item = new HeldItem(app, x,y, imagePath); // makes an item using the constructor
    }
    
    /**
     * Method for moving character
     * @param dx horizontal movement base speed
     * @param dy vertical movement base speed
     */
    public void move (int dx, int dy) {
        // dx and dy are multiplied by speed in order to get final move speed
        x += dx * speed;
        y += dy * speed;
    }
    
    /**
     * Draws character object using image from file and (x,y) coordinates
     */
    public void draw () {
        app.image(image, x, y);
    }
    
    /**
     * Method for rectangular collision detection
     * @param c2 c2 is checked to see if it collides with another character
     * @return true or false is returned depending on if the characters collide
     */
    public boolean isCollidingWith (Character c2) {
        // Collision checks
        boolean leftOfC2Right = x < c2.x + c2.image.width; // to the left of c2
        boolean rightOfC2Left = x + image.width > c2.x; // to the right of c2
        boolean aboveC2Bottom = y < c2.y + c2.image.height; // above c2
        boolean belowC2Top = y + image.height > c2.y; // below c2
        
        // Returning values
        return leftOfC2Right && rightOfC2Left
                && aboveC2Bottom && belowC2Top;
    }
    
    /**
     * Displays character's name and speed when called
     * @return A formatted sentence is returned with name and speed
     */
    public String toString() {
        return "Name: " + name + " | Speed: " + speed;
    }
}