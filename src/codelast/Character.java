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
    private HeldItem item; // If character needs to carry an item
    
    // Constructor
    public Character (PApplet p, int x, int y, int speed, String name, String imagePath) {
        app = p;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.name = name;
        image = app.loadImage(imagePath);
    }
    
    //Alternate constructor for objects
    public Character (PApplet p, int x, int y, String imagePath) { 
        app = p;
        this.x = x;
        this.y = y;
        image = app.loadImage(imagePath);
        item = new HeldItem(app, x,y, imagePath);
    }
    
    // Behaviour
    public void move (int dx, int dy) {
        x += dx * speed;
        y += dy * speed;
    }
    
    public void draw () {
        app.image(image, x, y);
    }
    
    // Rectangle collision detection
    public boolean isCollidingWith (Character c2) {
        // Collision checks
        boolean leftOfC2Right = x < c2.x + c2.image.width;
        boolean rightOfC2Left = x + image.width > c2.x;
        boolean aboveC2Bottom = y < c2.y + c2.image.height;
        boolean belowC2Top = y + image.height > c2.y;
        
        // Returning values
        return leftOfC2Right && rightOfC2Left
                && aboveC2Bottom && belowC2Top;
    }
    
    public String toString() {
        return "Name: " + name + " | Speed: " + speed;
    }
}