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
    private int x;
    private int y;
    private int speed;
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
    
    // Behaviour
    public void move (int dx, int dy) {
        x += dx * speed;
        y += dy * speed;
    }
    
    public void draw () {
        app.image(image, x, y);
    }
}